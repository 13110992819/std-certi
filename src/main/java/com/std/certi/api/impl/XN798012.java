/**
 * @Title XN798012.java 
 * @Package com.std.certi.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年2月23日 下午9:32:21 
 * @version V1.0   
 */
package com.std.certi.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.certi.ao.IIdentityAO;
import com.std.certi.api.AProcessor;
import com.std.certi.common.JsonUtil;
import com.std.certi.dto.req.XN798012Req;
import com.std.certi.exception.BizException;
import com.std.certi.exception.ParaException;
import com.std.certi.spring.SpringContextHolder;

/** 
 * 根据biz_no查询实名认证状态
 * @author: haiqingzheng 
 * @since: 2017年2月23日 下午9:32:21 
 * @history:
 */
public class XN798012 extends AProcessor {

    private IIdentityAO identityAO = SpringContextHolder
        .getBean(IIdentityAO.class);

    private XN798012Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return identityAO.doZhimaQuery(req.getSystemCode(),
            req.getCompanyCode(), req.getBizNo());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN798012Req.class);
        if (StringUtils.isBlank(req.getSystemCode())) {
            throw new ParaException("xn000000", "系统编号不能为空");
        }
        if (StringUtils.isBlank(req.getCompanyCode())) {
            throw new ParaException("xn000000", "公司编号不能为空");
        }
        if (StringUtils.isBlank(req.getBizNo())) {
            throw new ParaException("xn000000", "芝麻认证标识biz_no不能为空");
        }
    }
}
