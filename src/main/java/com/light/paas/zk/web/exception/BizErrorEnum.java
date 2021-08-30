package com.light.paas.zk.web.exception;

public enum BizErrorEnum {

    DEFAULT_BIZ_ERROR("99999", "系统异常"),
    TARGET_NODE_NOT_EXISTS("1", "节点不存在"),
    ;
    private final String errorCode;
    private final String errorMessage;

    BizErrorEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
