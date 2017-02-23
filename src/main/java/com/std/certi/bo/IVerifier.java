package com.std.certi.bo;

import com.alipay.api.AlipayClient;
import com.std.certi.domain.VerifyResult;
import com.std.certi.domain.ZhimaVerifyResult;

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

    /**
     * 四要素调用三方接口
     * @param idKind
     * @param idNo
     * @param realName
     * @param cardNo
     * @param bindMobile
     * @return 
     * @create: 2017年1月3日 下午5:15:03 xieyj
     * @history:
     */
    VerifyResult doVerify(String idKind, String idNo, String realName,
            String cardNo, String bindMobile);

    public String getZhimaBizNo(AlipayClient alipayClient, String realName,
            String idNo);

    public String getZhimaVerifyURL(AlipayClient alipayClient,
            String returnUrl, String bizNo);

    public ZhimaVerifyResult doVerify(AlipayClient alipayClient, String bizNo);
}
