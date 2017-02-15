package com.cifpay.lc.gateway.common.exception;

/**
 * Gateway层的顶级异常类，所有Gateway新定义的异常必须派生自此异常类。
 * <p>
 * ReturnCodes中定义了所有的返回码常量值。
 *
 * @see com.cifpay.lc.constant.ReturnCode
 */
public abstract class AbstractGatewayException extends RuntimeException {
    private static final long serialVersionUID = -8036169885994447875L;
    private int returnCode;

    public AbstractGatewayException(int returnCode, String message) {
        super(message);
        this.returnCode = returnCode;
    }

    public AbstractGatewayException(int returnCode, String message, Throwable cause) {
        super(message, cause);
        this.returnCode = returnCode;
    }

    public AbstractGatewayException(int returnCode, Throwable cause) {
        super(cause);
        this.returnCode = returnCode;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public String getFormatedReturnCode() {
        boolean isValidateError = Math.abs(this.getReturnCode()) > 103100 && Math.abs(this.getReturnCode()) < 103200;
        if (isValidateError) {
            return AbstractGatewayException.getFormatedReturnCode(GatewayValidationRejectException.FORMATED_RETURN_CODE_PREFIX, this.getReturnCode());
        }

        return getFormatedReturnCodePrefix() + "-" + Math.abs(this.getReturnCode());
    }

    public static String getFormatedReturnCode(String prefix, int errorCode) {
        return prefix + "-" + Math.abs(errorCode);
    }

    /**
     * 用于格式化返回码，以便错误信息查看者可以快速地区分错误的大致范围。
     * 例如，假设错误码为-123456，实现将getFormatedReturnCodePrefix()设定为“VA”，那么，
     * 最终通过getMessage( )获取到的异常信息类似如下：
     * <p>
     * “[VA-123456]银信证当前所处状态不允此进行此操作”
     *
     * @return
     */
    protected abstract String getFormatedReturnCodePrefix();
}
