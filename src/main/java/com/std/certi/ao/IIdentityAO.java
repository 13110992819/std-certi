package com.std.certi.ao;


public interface IIdentityAO {

    public void doTwoVerify(String systemCode, String companyCode,
            String userId, String idKind, String idNo, String realName,
            String remark);

    public void doFourVerify(String systemCode, String companyCode,
            String userId, String idKind, String idNo, String realName,
            String cardNo, String bindMobile, String remark);

    public Object doZhimaVerify(String systemCode, String companyCode,
            String userId, String idKind, String idNo, String realName,
            String remark);

    public boolean doZhimaQuery(String systemCode, String companyCode,
            String bizNo);

}
