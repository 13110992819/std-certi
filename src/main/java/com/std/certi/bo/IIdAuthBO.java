package com.std.certi.bo;

import com.std.certi.domain.IdAuth;

/**
 * 
 * @author: myb858 
 * @since: 2015年8月20日 下午2:33:00 
 * @history:
 */

public interface IIdAuthBO {

    /**
     * 保存真实认证结果
     * @param idKind
     * @param idNo
     * @param realName
     * @param cardNo
     * @param bindMobile 
     * @create: 2017年2月22日 下午3:22:52 myb858
     * @history:
     */
    public void doSave(String idKind, String idNo, String realName,
            String cardNo, String bindMobile);

    /**
     * 获取真实认证结果
     * @param idKind
     * @param idNo
     * @param realName
     * @param cardNo
     * @param bindMobile
     * @return 
     * @create: 2017年1月3日 下午4:57:05 xieyj
     * @history:
     */
    public IdAuth doGet(String idKind, String idNo, String realName,
            String cardNo, String bindMobile);
}
