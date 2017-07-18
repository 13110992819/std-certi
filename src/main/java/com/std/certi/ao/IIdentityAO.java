package com.std.certi.ao;

import com.std.certi.dto.res.XN798011Res;
import com.std.certi.dto.res.XN798012Res;

public interface IIdentityAO {

    public void doTwoVerify(String systemCode, String companyCode,
            String userId, String idKind, String idNo, String realName,
            String remark);

    public void doFourVerify(String systemCode, String companyCode,
            String userId, String idKind, String idNo, String realName,
            String cardNo, String bindMobile, String remark);

    public XN798011Res doZhimaVerify(String systemCode, String companyCode,
            String userId, String idKind, String idNo, String realName,
            String returnUrl, String remark);

    public XN798012Res doZhimaQuery(String systemCode, String companyCode,
            String bizNo);

}
