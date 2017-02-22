package com.std.certi.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.certi.ao.IUserPictureAO;
import com.std.certi.api.AProcessor;
import com.std.certi.common.JsonUtil;
import com.std.certi.core.StringValidater;
import com.std.certi.dto.req.XN798106Req;
import com.std.certi.exception.BizException;
import com.std.certi.exception.ParaException;
import com.std.certi.spring.SpringContextHolder;

/**
 * 获取人工实名认证--详情
 * @author: myb858 
 * @since: 2017年2月21日 下午9:15:33 
 * @history:
 */
public class XN798106 extends AProcessor {

    private IUserPictureAO userPictureAO = SpringContextHolder
        .getBean(IUserPictureAO.class);

    private XN798106Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return userPictureAO
            .getUserPicture(StringValidater.toLong(req.getId()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN798106Req.class);
        if (StringUtils.isBlank(req.getId())) {
            throw new ParaException("xn000000", "编号不能为空");
        }

    }

}
