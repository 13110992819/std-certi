package com.std.certi.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.certi.ao.IUserPictureAO;
import com.std.certi.api.AProcessor;
import com.std.certi.common.IdCardChecker;
import com.std.certi.common.JsonUtil;
import com.std.certi.dto.req.XN798100Req;
import com.std.certi.dto.res.BooleanRes;
import com.std.certi.enums.EIDKind;
import com.std.certi.exception.BizException;
import com.std.certi.exception.ParaException;
import com.std.certi.spring.SpringContextHolder;

/**
 * 人工实名认证--上传资料
 * @author: myb858 
 * @since: 2015年11月1日 下午3:30:32 
 * @history:
 */
public class XN798100 extends AProcessor {
    private IUserPictureAO userPictureAO = SpringContextHolder
        .getBean(IUserPictureAO.class);

    private XN798100Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userPictureAO.doSaveUserPicture(req.getSystemCode(),
            req.getCompanyCode(), req.getUserId(), req.getIdKind(),
            req.getIdNo(), req.getRealName(), req.getIdPic1(), req.getIdPic2(),
            req.getIdUserPic(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN798100Req.class);

        if (StringUtils.isBlank(req.getSystemCode())) {
            throw new ParaException("xn000000", "系统编号不能为空");
        }
        if (StringUtils.isBlank(req.getCompanyCode())) {
            throw new ParaException("xn000000", "系统编号不能为空");
        }
        if (StringUtils.isBlank(req.getUserId())) {
            throw new ParaException("xn000000", "用户编号不能为空");
        }
        if (StringUtils.isBlank(req.getRealName())) {
            throw new ParaException("xn000000", "真实姓名不能为空");
        }

        if (StringUtils.isBlank(req.getIdPic1())) {
            throw new ParaException("xn000000", "证件照片1不能为空");
        }
        if (StringUtils.isBlank(req.getIdPic2())) {
            throw new ParaException("xn000000", "证件照片2不能为空");
        }
        if (StringUtils.isBlank(req.getIdUserPic())) {
            throw new ParaException("xn000000", "证件照片3不能为空");
        }
        if (!EIDKind.IDCard.getCode().equalsIgnoreCase(req.getIdKind())) {
            throw new BizException("xn702000", "证件类型暂只支持身份证");
        }
        // 验证身份证号是否正确
        IdCardChecker idCardChecker = new IdCardChecker(req.getIdNo());
        if (!idCardChecker.validate()) {
            throw new BizException("xn702000", "身份证号格式不正确");
        }
    }
}
