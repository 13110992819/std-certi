package com.std.certi.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.certi.bo.IGatewayIdAuthLogBO;
import com.std.certi.bo.base.PaginableBOImpl;
import com.std.certi.common.DateUtil;
import com.std.certi.common.PropertiesUtil;
import com.std.certi.dao.IGatewayIdAuthLogDAO;
import com.std.certi.domain.GatewayIdAuthLog;
import com.std.certi.domain.VerifyResult;
import com.std.certi.exception.BizException;

@Component
public class GatewayIdAuthLogBOImpl extends PaginableBOImpl<GatewayIdAuthLog>
        implements IGatewayIdAuthLogBO {
    static Logger logger = Logger.getLogger(GatewayIdAuthLogBOImpl.class);

    @Autowired
    private IGatewayIdAuthLogDAO gatewayIdAuthLogDAO;

    @Override
    public void checkConfig(String systemCode, String companyCode, String userId) {
        // 校验间隔时间
        String maxValidTime = PropertiesUtil.getProperty("maxValidTime");
        Integer iMaxValidTime = StringUtils.isEmpty(maxValidTime) ? 60
                : Integer.valueOf(maxValidTime); // 间隔时间默认为60s
        logger.debug("maxValidTime===>>" + iMaxValidTime);
        GatewayIdAuthLog last = this
            .getLastOne(systemCode, companyCode, userId);
        if (last != null) {
            Date createDatetime = last.getCreateDatetime();
            Long m = DateUtil.daysBetween(createDatetime, new Date());
            if (m != null && m < iMaxValidTime) {
                throw new BizException("xn798000", "请于" + iMaxValidTime
                        + "秒之后，再次尝试");
            }
        }
        // 校验最大次数
        String maxInvokeCount = PropertiesUtil.getProperty("maxInvokeCount");
        Integer iMaxInvokeCount = StringUtils.isEmpty(maxInvokeCount) ? 3
                : Integer.valueOf(maxInvokeCount);// 最大调用次数默认为3次
        logger.debug("maxInvokeCount===>>" + iMaxInvokeCount);
        long count = this.getCount(systemCode, companyCode, userId);
        if (count >= iMaxInvokeCount) {
            throw new BizException("xn798000", "实名认证超过上限" + iMaxInvokeCount
                    + "次，请使用人工方式进行认证");
        }
    }

    private GatewayIdAuthLog getLastOne(String systemCode, String companyCode,
            String userId) {
        GatewayIdAuthLog result = null;
        if (StringUtils.isBlank(systemCode) && StringUtils.isNotBlank(userId)) {
            GatewayIdAuthLog condition = new GatewayIdAuthLog();
            condition.setSystemCode(systemCode);
            condition.setCompanyCode(companyCode);
            condition.setUserId(userId);
            List<GatewayIdAuthLog> list = gatewayIdAuthLogDAO
                .selectList(condition);
            if (CollectionUtils.isNotEmpty(list)) {
                result = list.get(0);
            }
        }
        return result;
    }

    private long getCount(String systemCode, String companyCode, String userId) {
        GatewayIdAuthLog condition = new GatewayIdAuthLog();
        condition.setSystemCode(systemCode);
        condition.setCompanyCode(companyCode);
        condition.setUserId(userId);
        return gatewayIdAuthLogDAO.selectTotalCount(condition);
    }

    @Override
    public void doSave(String systemCode, String companyCode, String userId,
            String idKind, String idNo, String realName, String cardNo,
            String bindMobile, String zhimaBizNo, String zhimaUrl,
            String remark, VerifyResult result) {
        GatewayIdAuthLog data = new GatewayIdAuthLog();
        data.setSystemCode(systemCode);
        data.setCompanyCode(companyCode);
        data.setUserId(userId);
        data.setIdKind(idKind);
        data.setIdNo(idNo);
        data.setRealName(realName);
        data.setCardNo(cardNo);
        data.setBindMobile(bindMobile);
        data.setZhimaBizNo(zhimaBizNo);
        data.setZhimaUrl(zhimaUrl);
        data.setRemark(remark);
        data.setErrorCode(result.getErrorCode());
        data.setErrorMsg(result.getErrorMsg());
        data.setCreateDatetime(new Date());
        gatewayIdAuthLogDAO.insert(data);
    }

    @Override
    public GatewayIdAuthLog getGatewayIdAuthLogByBizNo(String zhimaBizNo) {
        GatewayIdAuthLog gatewayIdAuthLog = null;
        if (StringUtils.isNotBlank(zhimaBizNo)) {
            GatewayIdAuthLog condition = new GatewayIdAuthLog();
            condition.setZhimaBizNo(zhimaBizNo);
            List<GatewayIdAuthLog> lists = gatewayIdAuthLogDAO
                .selectList(condition);
            if (lists.size() > 0) {
                gatewayIdAuthLog = lists.get(0);
            } else {
                throw new BizException("xn798012", "根据芝麻认证bizNo获取实名日志记录失败");
            }
        }
        return gatewayIdAuthLog;
    }

    @Override
    public int refreshErrorInfo(Long id, String errorCode, String errorMsg) {
        GatewayIdAuthLog data = new GatewayIdAuthLog();
        data.setErrorCode(errorCode);
        data.setErrorMsg(errorMsg);
        data.setId(id);
        return gatewayIdAuthLogDAO.updateErrorInfo(data);
    }
}
