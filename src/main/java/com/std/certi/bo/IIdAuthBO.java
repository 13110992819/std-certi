package com.std.certi.bo;

import com.std.certi.domain.IdAuth;

/**
 * 
 * @author: myb858 
 * @since: 2015年8月20日 下午2:33:00 
 * @history:
 */

public interface IIdAuthBO {

    // public void doSave(String systemId, String userId, String idKind,
    // String idNo, String realName, String cardNo, String bindMobile);
    //
    // public IdAuth doGetIdAuth(String idKind, String idNo, String realName);

    /**
     * 保存实名认证
     * @param systemId
     * @param userId
     * @param idKind
     * @param idNo
     * @param realName
     * @param cardNo
     * @param bindMobile 
     * @create: 2017年1月3日 下午4:56:36 xieyj
     * @history:
     */
    public void doSave(String systemId, String userId, String idKind,
            String idNo, String realName, String cardNo, String bindMobile);

    /**
     * 获取实名认证信息
     * @param idKind
     * @param idNo
     * @param realName
     * @param cardNo
     * @param bindMobile
     * @return 
     * @create: 2017年1月3日 下午4:57:05 xieyj
     * @history:
     */
    public IdAuth doGetIdAuth(String idKind, String idNo, String realName,
            String cardNo, String bindMobile);
}
