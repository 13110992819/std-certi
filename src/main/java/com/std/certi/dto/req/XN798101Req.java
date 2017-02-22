package com.std.certi.dto.req;

public class XN798101Req {
    // 编号(必填)
    private String id;

    // 验证人(必填)
    private String verifyUser;

    // 验证结果（1=通过；2=不通过）(必填)
    private String verifyResult;

    // 备注(选填)
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVerifyUser() {
        return verifyUser;
    }

    public void setVerifyUser(String verifyUser) {
        this.verifyUser = verifyUser;
    }

    public String getVerifyResult() {
        return verifyResult;
    }

    public void setVerifyResult(String verifyResult) {
        this.verifyResult = verifyResult;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
