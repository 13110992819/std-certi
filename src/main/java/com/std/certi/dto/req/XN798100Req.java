package com.std.certi.dto.req;

public class XN798100Req {
    // 系统编号(必填)
    private String systemCode;

    // 公司编号(必填)
    private String companyCode;

    // userid(必填)
    private String userId;

    // 证件类型(必填)
    private String idKind;

    // 证件号码(必填)
    private String idNo;

    // 真实姓名(必填)
    private String realName;

    // 证件照正面(必填)
    private String idPic1;

    // 证件照背面(必填)
    private String idPic2;

    // 本人手持证件照片(必填)
    private String idUserPic;

    // 备注(选填)
    private String remark;

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdKind() {
        return idKind;
    }

    public void setIdKind(String idKind) {
        this.idKind = idKind;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdPic1() {
        return idPic1;
    }

    public void setIdPic1(String idPic1) {
        this.idPic1 = idPic1;
    }

    public String getIdPic2() {
        return idPic2;
    }

    public void setIdPic2(String idPic2) {
        this.idPic2 = idPic2;
    }

    public String getIdUserPic() {
        return idUserPic;
    }

    public void setIdUserPic(String idUserPic) {
        this.idUserPic = idUserPic;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
