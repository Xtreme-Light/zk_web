package com.light.paas.zk.web.exception;

/**
 * @ Description   :  异常工厂实现
 * @ Author        :  da.xue
 * @ CreateDate    :  2020/04/11
 * @ Version       :  1.0
 */
public class ExceptionFactory {

    public static BizException bizException(String errorMessage) {
        return new BizException(errorMessage);
    }

    public static BizException bizException(String errorCode, String errorMessage) {
        return new BizException(errorCode, errorMessage);
    }
    public static BizException bizException(String errorMessage,Throwable e) {
        return new BizException(errorMessage, e);
    }
    public static BizException bizException(BizErrorEnum bizErrorEnum) {
        return new BizException(bizErrorEnum.getErrorCode(), bizErrorEnum.getErrorMessage());
    }



    public static SysException sysException(String errorMessage) {
        return new SysException(errorMessage);
    }

    public static SysException sysException(String errorCode, String errorMessage) {
        return new SysException(errorCode, errorMessage);
    }

    public static SysException sysException(String errorMessage, Throwable e) {
        return new SysException(errorMessage, e);
    }

    public static SysException sysException(String errorCode, String errorMessage, Throwable e) {
        return new SysException(errorCode, errorMessage, e);
    }

}
