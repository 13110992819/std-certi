package com.std.certi.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.certi.ao.IUserPictureAO;
import com.std.certi.api.AProcessor;
import com.std.certi.common.DateUtil;
import com.std.certi.common.JsonUtil;
import com.std.certi.core.StringValidater;
import com.std.certi.domain.UserPicture;
import com.std.certi.dto.req.XN798105Req;
import com.std.certi.exception.BizException;
import com.std.certi.exception.ParaException;
import com.std.certi.spring.SpringContextHolder;

/**
 * 分页获取人工实名认证
 * @author: myb858 
 * @since: 2015年10月27日 下午4:19:08 
 * @history:
 */
public class XN798105 extends AProcessor {

    private IUserPictureAO userPictureAO = SpringContextHolder
        .getBean(IUserPictureAO.class);

    private XN798105Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        UserPicture condition = new UserPicture();
        condition.setSystemCode(req.getSystemCode());
        condition.setCompanyCode(req.getCompanyCode());
        condition.setUserId(req.getUserId());

        condition.setIdKind(req.getIdKind());
        condition.setIdNo(req.getIdNo());
        condition.setRealName(req.getRealName());

        condition.setStatus(req.getStatus());
        condition.setVerifyUser(req.getVerifyUser());

        condition.setCreateDatetimeStart(DateUtil.getFrontDate(
            req.getDateStart(), false));
        condition.setCreateDatetimeEnd(DateUtil.getFrontDate(req.getDateEnd(),
            true));
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IUserPictureAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return userPictureAO.queryUserPicturePage(start, limit, condition);

    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN798105Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
