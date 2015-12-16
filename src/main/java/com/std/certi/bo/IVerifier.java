package com.std.certi.bo;

import com.std.certi.domain.VerifyResult;

public interface IVerifier {
    /**
     * 调用三方接口
     * @param idKind
     * @param idNo
     * @param realName
     * @return 
     * @create: 2015年12月4日 上午11:36:15 myb858
     * @history:
     */
    VerifyResult doVerify(String idKind, String idNo, String realName);

}
