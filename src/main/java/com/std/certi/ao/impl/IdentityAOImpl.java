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
import com.std.certi.enums.EFourVerifyCode;
import com.std.certi.enums.EVerifyCode;
import com.std.certi.exception.BizException;

@Service
public class IdentityAOImpl implements IIdentityAO {
    @Autowired
    IGatewayIdAuthLogBO gatewayIdAuthLogBO;

    @Autowired
    IIdAuthBO idAuthBO;

    @Autowired
    IVerifier verifier;

    @Override
    @Transactional
    public void doFourVerify(String systemCode, String companyCode,
            String userId, String idKind, String idNo, String realName,
            String cardNo, String bindMobile, String remark) {
        // 认证本地
        VerifyResult result = null;
        IdAuth idAuth = idAuthBO.doGet(idKind, idNo, realName, cardNo,
            bindMobile);
        if (idAuth != null) {// 本地认证通过
            result = new VerifyResult();
            result.setErrorCode(EFourVerifyCode.Pass.getCode());
            result.setErrorMsg(EFourVerifyCode.Pass.getValue());
        } else {
            // 是否可以调用认证接口
            gatewayIdAuthLogBO.checkConfig(systemCode, companyCode, userId);
            // 调用认证接口
            result = verifier.doVerify(idKind, idNo, realName, cardNo,
                bindMobile);
            // 处理结果记录日志
            gatewayIdAuthLogBO.doSave(systemCode, companyCode, userId, idKind,
                idNo, realName, cardNo, bindMobile, remark, result);
            // 如果成功：结果表落地
            if (EFourVerifyCode.Pass.getCode().equalsIgnoreCase(
                result.getErrorCode())) {
                idAuthBO.doSave(idKind, idNo, realName, cardNo, bindMobile);
            } else {
                throw new BizException("xn798006", "认证失败，失败原因："
                        + result.getErrorMsg());
            }
        }
    }

    @Override
    @Transactional
    public void doTwoVerify(String systemCode, String companyCode,
            String userId, String idKind, String idNo, String realName,
            String remark) {
        // 本地验证：不限次数
        VerifyResult result = null;
        IdAuth idAuth = idAuthBO.doGet(idKind, idNo, realName, null, null);
        if (idAuth != null) {// 本地认证通过
            result = new VerifyResult();
            result.setErrorCode(EVerifyCode.Pass.getCode());
            result.setErrorMsg(EVerifyCode.Pass.getValue());
        } else {
            // 是否可以调用认证接口
            gatewayIdAuthLogBO.checkConfig(systemCode, companyCode, userId);
            // 调用认证接口
            result = verifier.doVerify(idKind, idNo, realName);
            // DB落地
            // 日志落地
            gatewayIdAuthLogBO.doSave(systemCode, companyCode, userId, idKind,
                idNo, realName, null, null, remark, result);
            // 如果成功：结果表落地
            if (EVerifyCode.Pass.getCode().equalsIgnoreCase(
                result.getErrorCode())) {
                idAuthBO.doSave(idKind, idNo, realName, null, null);
            } else {
                throw new BizException("xn798001", "认证失败，失败原因："
                        + result.getErrorMsg());
            }
        }
    }
}
