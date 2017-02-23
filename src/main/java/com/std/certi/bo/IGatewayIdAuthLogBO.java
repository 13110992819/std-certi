package com.std.certi.bo;

import com.std.certi.bo.base.IPaginableBO;
import com.std.certi.domain.GatewayIdAuthLog;
import com.std.certi.domain.VerifyResult;

/**
 * 
 * @author: myb858 
 * @since: 2015年8月20日 下午2:33:00 
 * @history:
 */
public interface IGatewayIdAuthLogBO extends IPaginableBO<GatewayIdAuthLog> {

    /**
     *  判断是否符合配置条件
     * @param systemCode
     * @param companyCode
     * @param userId 
     * @create: 2017年2月22日 下午8:10:12 myb858
     * @history:
     */
    public void checkConfig(String systemCode, String companyCode, String userId);

    /**
     * 记录日志
     * @param systemCode
     * @param companyCode
     * @param userId
     * @param idKind
     * @param idNo
     * @param realName
     * @param cardNo
     * @param bindMobile
     * @param zhimaBizNo
     * @param zhimaUrl
     * @param remark
     * @param result 
     * @create: 2017年2月23日 下午10:48:29 haiqingzheng
     * @history:
     */
    public void doSave(String systemCode, String companyCode, String userId,
            String idKind, String idNo, String realName, String cardNo,
            String bindMobile, String zhimaBizNo, String zhimaUrl,
            String remark, VerifyResult result);

    public GatewayIdAuthLog getGatewayIdAuthLogByBizNo(String bizNo);

    public int refreshErrorInfo(Long id, String errorCode, String errorMsg);
}
