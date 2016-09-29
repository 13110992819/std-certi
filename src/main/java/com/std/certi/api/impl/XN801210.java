package com.std.certi.api.impl;

import com.std.user.ao.IIdentityAO;
import com.std.user.api.AProcessor;
import com.std.user.common.IdCardChecker;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN801210Req;
import com.std.user.dto.res.XN801210Res;
import com.std.user.enums.EIDKind;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 辅助-人工实名认证
 * @author: myb858 
 * @since: 2015年11月1日 下午3:30:32 
 * @history:
 */
public class XN801210 extends AProcessor {
    private IIdentityAO dentityAO = SpringContextHolder
        .getBean(IIdentityAO.class);

    private XN801210Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        dentityAO.doSaveUserPicture(req.getUserId(), req.getRealName(),
            req.getIdKind(), req.getIdNo(), req.getIdPic1(), req.getIdPic2(),
            req.getIdUserPic());
        return new XN801210Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801210Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getRealName(),
            req.getIdKind(), req.getIdNo(), req.getIdPic1(), req.getIdPic2(),
            req.getIdUserPic());
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
