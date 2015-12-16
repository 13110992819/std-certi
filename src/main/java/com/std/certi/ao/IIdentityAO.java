package com.std.certi.ao;

import com.std.certi.domain.VerifyResult;

public interface IIdentityAO {
    public VerifyResult doVerifyLocal(String idKind, String idNo,
            String realName);

    public VerifyResult doVerify(String systemId, String userId, String idKind,
            String idNo, String realName);

    public void doSave(String systemId, String userId, String idKind,
            String idNo, String realName, String remark, VerifyResult result);

}
