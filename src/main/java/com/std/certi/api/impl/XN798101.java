package com.std.certi.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.certi.ao.IUserPictureAO;
import com.std.certi.api.AProcessor;
import com.std.certi.common.JsonUtil;
import com.std.certi.core.StringValidater;
import com.std.certi.dto.req.XN798101Req;
import com.std.certi.dto.res.BooleanRes;
import com.std.certi.exception.BizException;
import com.std.certi.exception.ParaException;
import com.std.certi.spring.SpringContextHolder;

/**
 * 人工实名认证--审核资料
 * @author: myb858 
 * @since: 2015年10月27日 下午4:20:56 
 * @history:
 */
public class XN798101 extends AProcessor {

    private IUserPictureAO userPictureAO = SpringContextHolder
        .getBean(IUserPictureAO.class);

    private XN798101Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userPictureAO.doVerifyUserPicture(StringValidater.toLong(req.getId()),
            req.getVerifyUser(), req.getVerifyResult(), req.getRemark());
        return new BooleanRes(true);

    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN798101Req.class);
        if (StringUtils.isBlank(req.getId())) {
            throw new ParaException("xn000000", "编号不能为空");
        }
        if (StringUtils.isBlank(req.getVerifyUser())) {
            throw new ParaException("xn000000", "验证人不能为空");
        }
        if (StringUtils.isBlank(req.getVerifyResult())) {
            throw new ParaException("xn000000", "验证结果不能为空");
        }
    }

}
