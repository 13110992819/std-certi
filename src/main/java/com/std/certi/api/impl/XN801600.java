package com.std.certi.api.impl;

import com.std.user.ao.IIdentityAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN801600Req;
import com.std.user.dto.res.XN801600Res;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 人工审批实名认证
 * @author: myb858 
 * @since: 2015年10月27日 下午4:20:56 
 * @history:
 */
public class XN801600 extends AProcessor {

    private IIdentityAO identityAO = SpringContextHolder
        .getBean(IIdentityAO.class);

    private XN801600Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        identityAO.doVerifyUserPicture(StringValidater.toLong(req.getId()),
            req.getVerifyUser(), req.getVerifyResult(), req.getRemark());
        return new XN801600Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801600Req.class);
        StringValidater.validateBlank(req.getId(), req.getVerifyUser(),
            req.getVerifyResult());
        StringValidater.validateNumber(req.getId());
    }

}
