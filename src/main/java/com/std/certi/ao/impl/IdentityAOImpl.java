package com.std.certi.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.certi.ao.IIdentityAO;
import com.std.certi.bo.IGatewayIdAuthLogBO;
import com.std.certi.bo.IIdAuthBO;
import com.std.certi.bo.IVerifier;
import com.std.certi.domain.IdAuth;
import com.std.certi.domain.VerifyResult;
import com.std.certi.enums.EVerifyCode;

@Service
public class IdentityAOImpl implements IIdentityAO {
    @Autowired
    IGatewayIdAuthLogBO gatewayIdAuthLogBO;

    @Autowired
    IIdAuthBO idAuthBO;

    @Autowired
    IVerifier verifier;

    @Override
    public VerifyResult doVerifyLocal(String idKind, String idNo,
            String realName) {
        VerifyResult result = null;
        IdAuth idAuth = idAuthBO.doGetIdAuth(idKind, idNo, realName);
        if (idAuth != null) {// 本地认证通过
            result = new VerifyResult();
            result.setErrorCode(EVerifyCode.Pass.getCode());
            result.setErrorMsg(EVerifyCode.Pass.getValue());
        }
        return result;
    }

    @Override
    @Transactional
    public VerifyResult doVerify(String systemId, String userId, String idKind,
            String idNo, String realName) {
        // 是否可以调用认证接口
        gatewayIdAuthLogBO.checkConfig(systemId, userId);
        // 调用认证接口
        VerifyResult result = verifier.doVerify(idKind, idNo, realName);
        return result;
    }

    @Override
    public void doSave(String systemId, String userId, String idKind,
            String idNo, String realName, String remark, VerifyResult result) {
        // 日志落地
        gatewayIdAuthLogBO.doSave(systemId, userId, idKind, idNo, realName,
            remark, result);
        // 如果成功：结果表落地
        if (EVerifyCode.Pass.getCode().equalsIgnoreCase(result.getErrorCode())) {
            idAuthBO.doSave(systemId, userId, idKind, idNo, realName);
        }
    }

}
