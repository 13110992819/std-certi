package com.std.certi.api.impl;

import com.std.certi.ao.IIdentityAO;
import com.std.certi.api.AProcessor;
import com.std.certi.exception.BizException;
import com.std.certi.exception.ParaException;
import com.std.certi.spring.SpringContextHolder;

/**
 * 分页获取人工实名认证日志
 * @author: myb858 
 * @since: 2015年10月27日 下午4:19:08 
 * @history:
 */
public class XN801707 extends AProcessor {

    private IIdentityAO identityAO = SpringContextHolder
        .getBean(IIdentityAO.class);

    // private XN801707Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        // UserPicture condition = new UserPicture();
        // condition.setId(StringValidater.toLong(req.getId()));
        // condition.setUserId(req.getUserId());
        // condition.setUserId(req.getUserId());
        // condition.setIdKind(req.getIdKind());
        // condition.setIdNo(req.getIdNo());
        // condition.setRealName(req.getRealName());
        // condition.setStatus(req.getStatus());
        // condition.setVerifyUser(req.getVerifyUser());
        // condition.setCreateDatetimeStart(DateUtil.getFrontDate(
        // req.getDateStart(), false));
        // condition.setCreateDatetimeEnd(DateUtil.getFrontDate(req.getDateEnd(),
        // true));
        // String column = req.getOrderColumn();
        // if (StringUtils.isBlank(column)) {
        // column = IIdentityAO.DEFAULT_ORDER_COLUMN;
        // }
        // condition.setOrder(column, req.getOrderDir());
        // int start = Integer.valueOf(req.getStart());
        // int limit = Integer.valueOf(req.getLimit());
        // return identityAO.queryUserPicturePage(start, limit, condition);
        return null;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        // req = JsonUtil.json2Bean(inputparams, XN801707Req.class);
        // StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
