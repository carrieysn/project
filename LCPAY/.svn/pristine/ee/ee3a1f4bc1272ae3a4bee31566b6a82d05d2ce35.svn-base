package com.cifpay.lc.std.domain.paychannel;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.LcTranStatus;

import java.io.Serializable;

/**
 * Created by sweet on 16-10-19.
 */
public class FreezeOutputBean<T extends Serializable> {
    private int returnCode = ReturnCode.UNDEFINED_RETURN_CODE;  // 业务受理不成功
    private String returnMsg = "代码级错误，业务方法未设置returnCode、returnMsg";

    private LcTranStatus lcTranStatus;  // 交易处理状态
    private String serialNo;            // 交易流水号

    private T data;             //  开证返回参数

    public LcTranStatus getLcTranStatus() {
        return lcTranStatus;
    }

    public void setLcTranStatus(LcTranStatus lcTranStatus) {
        this.lcTranStatus = lcTranStatus;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return ReturnCode.GENERAL_SUCCESS == this.getReturnCode();
    }
}
