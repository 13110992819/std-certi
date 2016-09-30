package com.std.certi.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.std.certi.ao.IIdentityAO;
import com.std.certi.api.AProcessor;
import com.std.certi.common.IdCardChecker;
import com.std.certi.common.JsonUtil;
import com.std.certi.domain.VerifyResult;
import com.std.certi.dto.req.XN798001Req;
import com.std.certi.dto.res.XN798001Res;
import com.std.certi.enums.EIDKind;
import com.std.certi.enums.EVerifyCode;
import com.std.certi.exception.BizException;
import com.std.certi.exception.ParaException;
import com.std.certi.spring.SpringContextHolder;

/**
 * 实名认证
 * @author: myb858 
 * @since: 2015年12月4日 上午11:57:18 
 * @history:
 */
public class XN798001 extends AProcessor {
    static Logger logger = Logger.getLogger(XN798001.class);

    private IIdentityAO identityAO = SpringContextHolder
        .getBean(IIdentityAO.class);

    private XN798001Req xn798001Req = null;

    @Override
    public Object doBusiness() throws BizException {
        XN798001Res res = new XN798001Res();// 必须new
        // 本地验证：不限次数
        VerifyResult result = identityAO.doVerifyLocal(xn798001Req.getIdKind(),
            xn798001Req.getIdNo(), xn798001Req.getRealName());
        if (result == null) {
            // 三方验证：有限制条件
            result = identityAO.doVerify(xn798001Req.getSystemId(),
                xn798001Req.getUserId(), xn798001Req.getIdKind(),
                xn798001Req.getIdNo(), xn798001Req.getRealName());
            // DB落地
            identityAO.doSave(xn798001Req.getSystemId(),
                xn798001Req.getUserId(), xn798001Req.getIdKind(),
                xn798001Req.getIdNo(), xn798001Req.getRealName(),
                xn798001Req.getRemark(), result);
        }
        if (result != null) {
            if (EVerifyCode.Pass.getCode().equalsIgnoreCase(
                result.getErrorCode())) {
                res.setIsSuccess(true);
            } else {
                throw new BizException("xn798001", "认证失败，失败原因:"
                        + result.getErrorMsg());
            }
        }
        return res;

    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn798001Req = JsonUtil.json2Bean(inputparams, XN798001Req.class);
        String systemId = xn798001Req.getSystemId();
        if (StringUtils.isBlank(systemId)) {
            throw new ParaException("xn798001", "系统编号不能为空");
        }
        // if (!ESystemId.P2P.getCode().equalsIgnoreCase(systemId)
        // && !ESystemId.CPZC.getCode().equalsIgnoreCase(systemId)) {
        // throw new ParaException("xn798001", "系统编号不存在");
        // }
        if (StringUtils.isBlank(xn798001Req.getUserId())) {
            throw new ParaException("xn798001", "用户编号不能为空");
        }
        if (StringUtils.isBlank(xn798001Req.getRealName())) {
            throw new ParaException("xn798001", "真实姓名不能为空");
        }
        if (StringUtils.isBlank(xn798001Req.getIdKind())) {
            throw new ParaException("xn798001", "证件类型不能为空");
        }
        if (!EIDKind.IDCard.getCode().equalsIgnoreCase(xn798001Req.getIdKind())) {
            throw new ParaException("xn702000", "证件类型暂只支持身份证");
        }
        if (StringUtils.isBlank(xn798001Req.getIdNo())) {
            throw new ParaException("xn798001", "证件号码不能为空");
        }
        IdCardChecker idCardChecker = new IdCardChecker(xn798001Req.getIdNo());
        if (!idCardChecker.validate()) {
            throw new ParaException("xn702000", "身份证号格式不正确");
        }
    }
}
