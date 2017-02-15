package com.cifpay.lc.core.db.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.Lc;

@Repository
public interface LcDao {

    Lc selectByPrimaryKey(Long lcId);

    void deleteByPrimaryKey(Long lcId);

    int insert(Lc lc);

    void insertSelective(Lc lc);

    boolean updateByPrimaryKeySelective(Lc lc);

    void updateByPrimaryKey(Lc lc);

    Lc selectByMidOrderId(@Param("mid") String merId, @Param("orderId") String orderId);

    /**
     * 获取前100条超过有效期需要自动失效的记录，不支持多次解付记录
     */
    List<Lc> selectSinglePay4ExpireInvalidity(@Param("lcStatus") String lcStatus);

    /**
     * 根据产品代码和银信证状态查询LC列表
     *
     * @param productCode
     * @param lcStatus
     * @return
     */
    List<Lc> selectListByProductCodeLcStatus(@Param("productCode") String productCode, @Param("lcStatus") String lcStatus);

    /**
     * 根据lcId和lcStatus，更新LC记录。
     *
     * @param updatingLc
     * @param lcId
     * @param lcStatus
     */
    int updateByLcIdLcStatusSelective(@Param("updatingLc") Lc updatingLc, @Param("lcId") Long lcId, @Param("lcStatus") String lcStatus);

    /**
     * 批量查询银信银信证记录
     *
     * @param lcIds
     * @return
     */
    List<Lc> selectByLcIds(@Param("lcIds") Long... lcIds);

    /**
     * 扣减LC余额。
     *
     * @param lcId
     * @param decreaseAmount
     * @param updateTime
     * @return 若返回的受影响记录大于0，则表示扣减成功，否则表示扣减失败
     */
    int decreaseLcBalance(@Param("lcId") Long lcId, @Param("decreaseAmount") BigDecimal decreaseAmount, @Param("updateTime") Date updateTime);

    /**
     * 冻结银信证金额（A版）可用余额 --> 冻结金额
     *
     * @param lcId
     * @param amount
     * @return 若返回的受影响记录大于0，则表示冻结成功，否则表示冻结失败
     */
    int freezeAmount(@Param("lcId") Long lcId, @Param("amount") BigDecimal amount);

    /**
     * 解冻银信证金额（A版）冻结金额 --> 可用余额
     *
     * @param lcId
     * @param amount
     * @return
     */
    int unfreezeAmount(@Param("lcId") Long lcId, @Param("amount") BigDecimal amount);

    /**
     * 减少银信证冻结金额（A版）冻结金额直接扣减
     *
     * @param lcId   银信证ID
     * @param amount 需减少的金额
     * @return 若返回的受影响记录大于0，则表示扣减成功，否则表示扣减失败
     */
    int decreaseFreezingAmount(@Param("lcId") Long lcId, @Param("amount") BigDecimal amount);

    /**
     * 获取“预失效”状态申请解付有效期过期的银信证
     */
    List<Lc> selectPreInvalidLcWithApplyValidTimeExpired();

    /**
     * 收证过期（已开证，未收证）
     */
    List<Lc> selectRecvExpired(@Param("offset") int offset, @Param("rows") int rows);

    /**
     * 履约过期（已收证，未履约）
     */
    List<Lc> selectAppointmentExpired(@Param("offset") int offset, @Param("rows") int rows);

    /**
     * 申请解付过期（已履约，未申请解付）
     */
    List<Lc> selectApplyExpired(@Param("offset") int offset, @Param("rows") int rows);

    /**
     * 多次解付履约过期
     */
    List<Lc> selectAppointmentExpired_Multiple();

    /**
     * 获取满足条件的预失效状态的银信证
     */
    List<Lc> selectPreInvalid2Invalid();

}
