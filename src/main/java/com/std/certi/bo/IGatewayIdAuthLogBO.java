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
     * 获取最近一个日志
     * @param systemId
     * @param userId
     * @return 
     * @create: 2015年12月4日 下午1:13:01 myb858
     * @history:
     */
    public GatewayIdAuthLog getLastOne(String systemId, String userId);

    /**
     * 根据条件获取个数
     * @param systemId
     * @param userId
     * @return 
     * @create: 2015年12月4日 下午1:13:42 myb858
     * @history:
     */
    public long getCount(String systemId, String userId);

    /**
     * 判断是否符合配置条件
     * @param systemId
     * @param userId
     * @return 
     * @create: 2015年12月4日 上午11:33:25 myb858
     * @history:
     */
    public void checkConfig(String systemId, String userId);

    /**
     * 记录日志
     * @param systemId
     * @param userId
     * @param idKind
     * @param idNo
     * @param realName
     * @param remark
     * @param result 
     * @create: 2015年12月4日 下午1:37:05 myb858
     * @history:
     */
    public void doSave(String systemId, String userId, String idKind,
            String idNo, String realName, String remark, VerifyResult result);
}
