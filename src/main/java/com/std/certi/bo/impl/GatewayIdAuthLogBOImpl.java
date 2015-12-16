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
    public GatewayIdAuthLog getLastOne(String systemId, String userId) {
        GatewayIdAuthLog result = null;
        if (StringUtils.isBlank(systemId) && StringUtils.isNotBlank(userId)) {
            GatewayIdAuthLog condition = new GatewayIdAuthLog();
            condition.setSystemId(systemId);
            condition.setUserId(userId);
            List<GatewayIdAuthLog> list = gatewayIdAuthLogDAO
                .selectList(condition);
            if (CollectionUtils.isNotEmpty(list)) {
                result = list.get(0);
            }
        }
        return result;
    }

    @Override
    public long getCount(String systemId, String userId) {
        GatewayIdAuthLog condition = new GatewayIdAuthLog();
        condition.setSystemId(systemId);
        condition.setUserId(userId);
        return gatewayIdAuthLogDAO.selectTotalCount(condition);
    }

    @Override
    public void checkConfig(String systemId, String userId) {
        // 校验间隔时间
        String maxValidTime = PropertiesUtil.getProperty("maxValidTime");
        Integer iMaxValidTime = StringUtils.isEmpty(maxValidTime) ? 60
                : Integer.valueOf(maxValidTime); // 间隔时间默认为60s
        logger.debug("maxValidTime===>>" + iMaxValidTime);
        GatewayIdAuthLog last = this.getLastOne(systemId, userId);
        if (last != null) {
            Date createDatetime = last.getCreateDatetime();
            Long m = DateUtil.daysBetween(createDatetime, new Date());
            if (m != null && m < iMaxValidTime) {
                throw new BizException("xn798001", "请于" + iMaxValidTime
                        + "秒之后，再次尝试");
            }
        }
        // 校验最大次数
        String maxInvokeCount = PropertiesUtil.getProperty("maxInvokeCount");
        Integer iMaxInvokeCount = StringUtils.isEmpty(maxInvokeCount) ? 3
                : Integer.valueOf(maxInvokeCount);// 最大调用次数默认为3次
        logger.debug("maxInvokeCount===>>" + iMaxInvokeCount);
        long count = this.getCount(systemId, userId);
        if (count >= iMaxInvokeCount) {
            throw new BizException("xn798001", "实名认证超过上限" + iMaxInvokeCount
                    + "次，请使用人工方式进行认证");
        }
    }

    @Override
    public void doSave(String systemId, String userId, String idKind,
            String idNo, String realName, String remark, VerifyResult result) {
        GatewayIdAuthLog data = new GatewayIdAuthLog();
        data.setSystemId(systemId);
        data.setUserId(userId);
        data.setIdKind(idKind);
        data.setIdNo(idNo);
        data.setRealName(realName);
        data.setRemark(remark);
        data.setErrorCode(result.getErrorCode());
        data.setErrorMsg(result.getErrorMsg());
        data.setCreateDatetime(new Date());
        gatewayIdAuthLogDAO.insert(data);
    }

}
