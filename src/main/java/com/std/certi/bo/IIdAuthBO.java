package com.std.certi.bo;

import com.std.certi.domain.IdAuth;

/**
 * 
 * @author: myb858 
 * @since: 2015年8月20日 下午2:33:00 
 * @history:
 */

public interface IIdAuthBO {

    public void doSave(String systemId, String userId, String idKind,
            String idNo, String realName);

    public IdAuth doGetIdAuth(String idKind, String idNo, String realName);

}
