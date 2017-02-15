package com.cifpay.lc.std.sched.task;


import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.constant.enums.MessageEnum;
import com.cifpay.lc.constant.enums.MessageEnum.Scene;
import com.cifpay.lc.core.autoconfigure.lock.DisLock;
import com.cifpay.lc.core.autoconfigure.lock.DistributeLockManager;
import com.cifpay.lc.core.db.dao.MessageDao;
import com.cifpay.lc.core.db.pojo.Message;
import com.cifpay.lc.core.interceptor.annotation.EnableGenerateRequestId;
import com.cifpay.lc.core.message.biz.MessagePublisher;
import com.cifpay.lc.domain.message.MessageInputBean;
import com.cifpay.lc.domain.message.RetryMessageOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@EnableGenerateRequestId
public abstract class BaseMessageTask {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public String DISTRIBUTE_LOCK_PREFIX = "/CIFPAY/LOCK/LCMESSAGE";

    @Autowired
    private DistributeLockManager distributeLockManager;

    private ExecutorService executor = Executors.newFixedThreadPool(20);

    @Autowired
    private MessageDao messageDao;

    public BusinessOutput<RetryMessageOutput> handleMessage(MessageEnum.MsgType msgType, MessageEnum.Scene scene) {
        int PAGE_SIZE = 50;
        //String scene = params.getScene();
        String lockPath = getLockName(scene); //DISTRIBUTE_LOCK_PREFIX + scene;
        //避免不同节点并行
        DisLock lock = null;
        try {
            lock = distributeLockManager.getDisLock(lockPath);
            if (lock.acquire(5, TimeUnit.SECONDS)) {
                boolean stop = false;
                while (!stop) {
                    Message queryParam = new Message();
                    queryParam.setMsgType(msgType.val);
                    queryParam.setScene(scene.val);
                    queryParam.setStatus(MessageEnum.Status.WAIT.val);
                    queryParam.setOrderField("PLAN_TIME ASC");
                    queryParam.setOffset(0);
                    queryParam.setSize(PAGE_SIZE);
                    queryParam.setPlanTime(System.currentTimeMillis());
                    List<Message> messages = messageDao.listMessageBySelective(queryParam);
                    if (messages == null || messages.size() <= 0) {
                        stop = true;
                        break;
                    }
                    for (Message message : messages) {
                        executor.submit(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Message record = new Message();
                                    record.setId(message.getId());
                                    record.setStatus(MessageEnum.Status.HANLDING.val);
                                    record.setLastTime(System.currentTimeMillis());
                                    record.setCount(message.getCount() + 1);
                                    boolean updateSuccess = messageDao.updateByPrimaryKeySelective(record);

                                    if (updateSuccess) {
                                        MessageInputBean messageInput = new MessageInputBean();
                                        messageInput.setMsgId(message.getId());
                                        messageInput.setMsgType(message.getMsgType());
                                        messageInput.setSence(message.getScene());

                                        MessagePublisher messagePublisher = getMessagePublisher();
                                        boolean sendSuccess = messagePublisher.send(messageInput);
                                        if (!sendSuccess) {
                                            logger.error("处理消息不成功,msgId:{}", message.getId());
                                        }
                                    } else {
                                        logger.error("更新消息:{}不成功!!!", message.getId());
                                    }

                                } catch (Exception e) {
                                    logger.error("处理消息失败了,msgId:{},异常：{}", message.getId(), e);
                                }
                            }
                        });
                    }
                }
            }
        } catch (Exception e) {
            logger.error("获取分布式锁出现异常,err:{}", e);
        } finally {
            distributeLockManager.unlock(lock);
        }

        return BusinessOutput.success(null);
    }

    public String getLockName(Scene scence) {
        return DISTRIBUTE_LOCK_PREFIX + "-" + scence.val;
    }

    public abstract MessagePublisher getMessagePublisher();
}
