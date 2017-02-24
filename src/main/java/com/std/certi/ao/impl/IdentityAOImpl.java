package com.std.certi.ao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.std.certi.ao.IIdentityAO;
import com.std.certi.bo.ICPasswordBO;
import com.std.certi.bo.IGatewayIdAuthLogBO;
import com.std.certi.bo.IIdAuthBO;
import com.std.certi.bo.IVerifier;
import com.std.certi.domain.GatewayIdAuthLog;
import com.std.certi.domain.IdAuth;
import com.std.certi.domain.VerifyResult;
import com.std.certi.domain.ZhimaVerifyResult;
import com.std.certi.dto.res.XN798011Res;
import com.std.certi.dto.res.XN798012Res;
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

    @Autowired
    ICPasswordBO cPasswordBO;

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
                idNo, realName, cardNo, bindMobile, null, null, remark, result);
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
                idNo, realName, null, null, null, null, remark, result);
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

    @Override
    @Transactional
    public XN798011Res doZhimaVerify(String systemCode, String companyCode,
            String userId, String idKind, String idNo, String realName,
            String remark) {
        XN798011Res xn798011Res = new XN798011Res();
        // 本地验证：不限次数
        VerifyResult result = null;
        IdAuth idAuth = idAuthBO.doGet(idKind, idNo, realName, null, null);
        if (idAuth != null) {// 本地认证通过
            result = new VerifyResult();
            result.setErrorCode(EFourVerifyCode.Pass.getCode());
            result.setErrorMsg(EFourVerifyCode.Pass.getValue());
            xn798011Res.setSuccess(true);
        } else {
            xn798011Res.setSuccess(false);
            // 取系统配置
            Map<String, String> passwordsMap = getPassword(systemCode,
                companyCode);
            AlipayClient alipayClient = getAlipayClient(passwordsMap);
            // 认证初始化，取得biz_no
            String bizNo = verifier.getZhimaBizNo(alipayClient, realName, idNo);
            // 开始认证，取得认证url
            String url = verifier.getZhimaVerifyURL(alipayClient,
                passwordsMap.get("return_url"), bizNo);
            xn798011Res.setBizNo(bizNo);
            xn798011Res.setUrl(url);
            // 日志落地
            gatewayIdAuthLogBO.doSave(systemCode, companyCode, userId, idKind,
                idNo, realName, null, null, bizNo, url, remark,
                new VerifyResult());

        }
        return xn798011Res;
    }

    @Override
    @Transactional
    public XN798012Res doZhimaQuery(String systemCode, String companyCode,
            String bizNo) {
        XN798012Res xn798012Res = new XN798012Res();
        Map<String, String> passwordsMap = getPassword(systemCode, companyCode);
        AlipayClient alipayClient = getAlipayClient(passwordsMap);
        // 如果日志errorCode、errorMsg为空，说明还未实名成功
        GatewayIdAuthLog gatewayIdAuthLog = gatewayIdAuthLogBO
            .getGatewayIdAuthLogByBizNo(bizNo);
        ZhimaVerifyResult result = verifier.doVerify(alipayClient, bizNo);
        // 更新结果
        gatewayIdAuthLogBO.refreshErrorInfo(gatewayIdAuthLog.getId(),
            result.getErrorCode(),
            result.getErrorMsg() + " 认证结果:" + result.isPassed());
        // 如果成功：结果表落地
        if (result.isPassed()) {
            idAuthBO.doSave(gatewayIdAuthLog.getIdKind(),
                gatewayIdAuthLog.getIdNo(), gatewayIdAuthLog.getRealName(),
                null, null);
        }
        xn798012Res.setSuccess(result.isPassed());
        xn798012Res.setIdKind(gatewayIdAuthLog.getIdKind());
        xn798012Res.setIdNo(gatewayIdAuthLog.getIdNo());
        xn798012Res.setRealName(gatewayIdAuthLog.getRealName());
        return xn798012Res;
    }

    private AlipayClient getAlipayClient(Map<String, String> passwordsMap) {
        return new DefaultAlipayClient(passwordsMap.get("url"),
            passwordsMap.get("app_id"), passwordsMap.get("app_private_key"),
            "json", "utf-8", passwordsMap.get("alipay_public_key"), "RSA2");

    }

    private Map<String, String> getPassword(String systemCode,
            String companyCode) {
        Map<String, String> passwordsMap = cPasswordBO.queryCPasswordMap(
            systemCode, companyCode, "1");
        if (passwordsMap.size() <= 0) {
            throw new BizException("xn000000", "获取芝麻认证配置失败，请仔细检查");
        }
        return passwordsMap;
    }
}
