package com.std.certi.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.std.certi.ao.IIdentityAO;
import com.std.certi.api.AProcessor;
import com.std.certi.common.IdCardChecker;
import com.std.certi.common.JsonUtil;
import com.std.certi.dto.req.XN798006Req;
import com.std.certi.dto.res.BooleanRes;
import com.std.certi.enums.EIDKind;
import com.std.certi.exception.BizException;
import com.std.certi.exception.ParaException;
import com.std.certi.spring.SpringContextHolder;

/**
 * 四要素实名认证
 * @author: xieyj 
 * @since: 2017年1月3日 下午5:24:00 
 * @history:
 */
public class XN798006 extends AProcessor {
    static Logger logger = Logger.getLogger(XN798006.class);

    private IIdentityAO identityAO = SpringContextHolder
        .getBean(IIdentityAO.class);

    private XN798006Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        identityAO.doFourVerify(req.getSystemId(), req.getUserId(),
            req.getIdKind(), req.getIdNo(), req.getRealName(), req.getCardNo(),
            req.getBindMobile(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN798006Req.class);
        if (StringUtils.isBlank(req.getSystemId())) {
            throw new ParaException("xn000000", "系统编号不能为空");
        }
        if (StringUtils.isBlank(req.getUserId())) {
            throw new ParaException("xn000000", "用户编号不能为空");
        }
        if (StringUtils.isBlank(req.getRealName())) {
            throw new ParaException("xn000000", "真实姓名不能为空");
        }
        if (StringUtils.isBlank(req.getIdKind())) {
            throw new ParaException("xn000000", "证件类型不能为空");
        }
        if (!EIDKind.IDCard.getCode().equalsIgnoreCase(req.getIdKind())) {
            throw new ParaException("xn000000", "证件类型暂只支持身份证");
        }
        if (StringUtils.isBlank(req.getIdNo())) {
            throw new ParaException("xn000000", "证件号码不能为空");
        }
        IdCardChecker idCardChecker = new IdCardChecker(req.getIdNo());
        if (!idCardChecker.validate()) {
            throw new ParaException("xn702000", "身份证号格式不正确");
        }
        if (StringUtils.isBlank(req.getCardNo())) {
            throw new ParaException("xn000000", "卡号不能为空");
        }
    }
}
