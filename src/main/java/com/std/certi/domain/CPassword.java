package com.std.certi.domain;

import com.std.certi.dao.base.ABaseDO;

public class CPassword extends ABaseDO {

    private static final long serialVersionUID = 1L;

    private String code;

    // 系统编号
    private String systemCode;

    // 公司编号
    private String companyCode;

    // 类型
    private String type;

    // key
    private String account;

    // value
    private String password;

    // 备注
    private String remark;

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
