package com.cifpay.lc.std.paychannel.impl;

import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.db.dao.AdminLcMerCreDao;
import com.cifpay.lc.core.db.pojo.AdminLcMerCre;
import com.cifpay.lc.core.exception.PaymentException;
import com.cifpay.lc.domain.enums.AdminCardType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractUnion {

    public static final String TXN_TIME_FORMAT = "yyyyMMddHHmmss";

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public final static String DEPOSIT = "10"; // 储蓄卡
    public final static String CREDIT = "30";// 信用卡

    @Autowired
    private AdminLcMerCreDao adminLcMerCreDao;

    /**
     * @return
     */
    protected abstract AdminCardType getCardType();

    /**
     * 查询二级商户号
     *
     * @param merId
     * @return
     * @throws Exception
     */
    protected AdminLcMerCre queryAdminLcMerCreList(String merId) throws PaymentException {

        List<AdminLcMerCre> adminLcMerCreList = adminLcMerCreDao.selectByMerCodeAndCardType(merId, getCardType().getCode());

        if (adminLcMerCreList == null || adminLcMerCreList.size() != 1) {
            throw new PaymentException(ReturnCode.CORE_STD_SECOND_MER_NOT_EXIST, "未找到对应的银联二级商户号");
        }

        return adminLcMerCreList.get(0);
    }
}
