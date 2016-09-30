package com.std.certi.api.impl;

import com.std.certi.ao.IIdentityAO;
import com.std.certi.api.AProcessor;
import com.std.certi.exception.BizException;
import com.std.certi.exception.ParaException;
import com.std.certi.spring.SpringContextHolder;

/**
 * 人工审批实名认证
 * @author: myb858 
 * @since: 2015年10月27日 下午4:20:56 
 * @history:
 */
public class XN801600 extends AProcessor {

    private IIdentityAO identityAO = SpringContextHolder
        .getBean(IIdentityAO.class);

    // private XN801600Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        // identityAO.doVerifyUserPicture(StringValidater.toLong(req.getId()),
        // req.getVerifyUser(), req.getVerifyResult(), req.getRemark());
        // return new XN801600Res(true);
        return null;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        // req = JsonUtil.json2Bean(inputparams, XN801600Req.class);
        // StringValidater.validateBlank(req.getId(), req.getVerifyUser(),
        // req.getVerifyResult());
        // StringValidater.validateNumber(req.getId());
    }

}
