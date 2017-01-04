package com.std.certi.domain;

public class VerifyResult {
    private String errorCode;

    private String errorMsg;

    public VerifyResult() {
    }

    public VerifyResult(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "VerifyResult [errorCode=" + errorCode + ", errorMsg="
                + errorMsg + "]";
    }

}
