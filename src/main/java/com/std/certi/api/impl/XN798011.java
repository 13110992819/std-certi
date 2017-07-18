package com.std.certi.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.certi.ao.IIdentityAO;
import com.std.certi.api.AProcessor;
import com.std.certi.common.IdCardChecker;
import com.std.certi.common.JsonUtil;
import com.std.certi.dto.req.XN798011Req;
import com.std.certi.enums.EIDKind;
import com.std.certi.exception.BizException;
import com.std.certi.exception.ParaException;
import com.std.certi.spring.SpringContextHolder;

//芝麻认证 
//第一步认证初始化，返回biz_no（biz_no是本次认证的标识，在后面的认证接口和查询接口会用到）
//第二步开始认证，返回认证URL供客户端使用
public class XN798011 extends AProcessor {

    private IIdentityAO identityAO = SpringContextHolder
        .getBean(IIdentityAO.class);

    private XN798011Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return identityAO.doZhimaVerify(req.getSystemCode(),
            req.getCompanyCode(), req.getUserId(), req.getIdKind(),
            req.getIdNo(), req.getRealName(), req.getReturnUrl(),
            req.getRemark());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN798011Req.class);
        if (StringUtils.isBlank(req.getSystemCode())) {
            throw new ParaException("xn000000", "系统编号不能为空");
        }
        if (StringUtils.isBlank(req.getCompanyCode())) {
            throw new ParaException("xn000000", "系统编号不能为空");
        }
        if (StringUtils.isBlank(req.getUserId())) {
            throw new ParaException("xn000000", "用户编号不能为空");
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
    }

}
