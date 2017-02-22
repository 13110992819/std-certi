package com.std.certi.bo.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.std.certi.bo.IVerifier;
import com.std.certi.domain.VerifyResult;
import com.std.certi.exception.BizException;
import com.std.certi.gateway.FourIdAuthHttpUtil;
import com.std.certi.gateway.IdAuthHttpUtil;

@Component
public class VerifierImpl implements IVerifier {
    static Logger logger = Logger.getLogger(VerifierImpl.class);

    @Override
    public VerifyResult doVerify(String idKind, String idNo, String realName) {
        VerifyResult result = null;
        try {
            result = IdAuthHttpUtil.httpParse(idNo, realName);
        } catch (Exception e) {
            throw new BizException("xn798001", "调用第三方实名认证时异常");
        }
        return result;
    }

    @Override
    public VerifyResult doVerify(String idKind, String idNo, String realName,
            String cardNo, String bindMobile) {
        VerifyResult result = null;
        try {
            result = FourIdAuthHttpUtil.httpParse(idNo, realName, cardNo,
                bindMobile);
        } catch (Exception e) {
            throw new BizException("xn798006", "调用第三方实名认证时异常");
        }
        return result;
    }
}
