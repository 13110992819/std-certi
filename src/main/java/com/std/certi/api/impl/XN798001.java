package com.std.certi.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.std.certi.ao.IIdentityAO;
import com.std.certi.api.AProcessor;
import com.std.certi.common.IdCardChecker;
import com.std.certi.common.JsonUtil;
import com.std.certi.dto.req.XN798001Req;
import com.std.certi.dto.res.BooleanRes;
import com.std.certi.enums.EIDKind;
import com.std.certi.exception.BizException;
import com.std.certi.exception.ParaException;
import com.std.certi.spring.SpringContextHolder;

/**
 * 二要素实名认证
 * @author: myb858 
 * @since: 2015年12月4日 上午11:57:18 
 * @history:
 */
public class XN798001 extends AProcessor {
    static Logger logger = Logger.getLogger(XN798001.class);

    private IIdentityAO identityAO = SpringContextHolder
        .getBean(IIdentityAO.class);

    private XN798001Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        identityAO.doTwoVerify(req.getSystemCode(), req.getCompanyCode(),
            req.getUserId(), req.getIdKind(), req.getIdNo(), req.getRealName(),
            req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN798001Req.class);

        if (StringUtils.isBlank(req.getSystemCode())) {
            throw new ParaException("xn000000", "系统编号不能为空");
        }
        if (StringUtils.isBlank(req.getCompanyCode())) {
            throw new ParaException("xn000000", "系统编号不能为空");
        }
        if (StringUtils.isBlank(req.getUserId())) {
            throw new ParaException("xn798001", "用户编号不能为空");
        }
        if (StringUtils.isBlank(req.getRealName())) {
            throw new ParaException("xn798001", "真实姓名不能为空");
        }
        if (StringUtils.isBlank(req.getIdKind())) {
            throw new ParaException("xn798001", "证件类型不能为空");
        }
        if (!EIDKind.IDCard.getCode().equalsIgnoreCase(req.getIdKind())) {
            throw new ParaException("xn702000", "证件类型暂只支持身份证");
        }
        if (StringUtils.isBlank(req.getIdNo())) {
            throw new ParaException("xn798001", "证件号码不能为空");
        }
        IdCardChecker idCardChecker = new IdCardChecker(req.getIdNo());
        if (!idCardChecker.validate()) {
            throw new ParaException("xn702000", "身份证号格式不正确");
        }
    }
}
