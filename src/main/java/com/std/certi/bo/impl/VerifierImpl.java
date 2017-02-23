package com.std.certi.bo.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.ZhimaCustomerCertificationCertifyRequest;
import com.alipay.api.request.ZhimaCustomerCertificationInitializeRequest;
import com.alipay.api.request.ZhimaCustomerCertificationQueryRequest;
import com.alipay.api.response.ZhimaCustomerCertificationCertifyResponse;
import com.alipay.api.response.ZhimaCustomerCertificationInitializeResponse;
import com.alipay.api.response.ZhimaCustomerCertificationQueryResponse;
import com.std.certi.bo.IVerifier;
import com.std.certi.core.OrderNoGenerater;
import com.std.certi.domain.VerifyResult;
import com.std.certi.domain.ZhimaVerifyResult;
import com.std.certi.exception.BizException;
import com.std.certi.gateway.FourIdAuthHttpUtil;
import com.std.certi.gateway.IdAuthHttpUtil;

@Component
public class VerifierImpl implements IVerifier {
    static Logger logger = Logger.getLogger(VerifierImpl.class);

    @Override
    public VerifyResult doVerify(String idKind, String idNo, String realName) {
        VerifyResult result = null;
        try {
            result = IdAuthHttpUtil.httpParse(idNo, realName);
        } catch (Exception e) {
            throw new BizException("xn798001", "调用第三方实名认证时异常");
        }
        return result;
    }

    @Override
    public VerifyResult doVerify(String idKind, String idNo, String realName,
            String cardNo, String bindMobile) {
        VerifyResult result = null;
        try {
            result = FourIdAuthHttpUtil.httpParse(idNo, realName, cardNo,
                bindMobile);
        } catch (Exception e) {
            throw new BizException("xn798006", "调用第三方实名认证时异常");
        }
        return result;
    }

    @Override
    public String getZhimaBizNo(AlipayClient alipayClient, String realName,
            String idNo) {
        String bizNo = null;
        ZhimaCustomerCertificationInitializeRequest request = new ZhimaCustomerCertificationInitializeRequest();
        String bizContent = "{"
                + "    \"transaction_id\":\""
                + OrderNoGenerater.generate("ZMRZ")
                + "\","
                + "    \"product_code\":\"w1010100000000002978\","
                + "    \"biz_code\":\"FACE\","
                + "    \"identity_param\":\"{\\\"identity_type\\\":\\\"CERT_INFO\\\",\\\"cert_type\\\":\\\"IDENTITY_CARD\\\",\\\"principal_id\\\":\\\"2394276742\\\",\\\"cert_name\\\":\\\""
                + realName + "\\\",\\\"cert_no\\\":\\\"" + idNo + "\\\"}\","
                + "    \"ext_biz_param\":\"{}\"" + "  }";
        request.setBizContent(bizContent);

        ZhimaCustomerCertificationInitializeResponse response;
        try {
            response = alipayClient.execute(request);
            if (response.isSuccess()) {
                System.out.println("调用成功");
                bizNo = response.getBizNo();
                System.out.println(bizNo);
            } else {
                System.out.println("调用失败");
            }
        } catch (AlipayApiException e) {
            throw new BizException("xn798011", "调用芝麻初始化时异常");
        }
        return bizNo;
    }

    @Override
    public String getZhimaVerifyURL(AlipayClient alipayClient,
            String returnUrl, String bizNo) {
        String url = null;
        ZhimaCustomerCertificationCertifyRequest request1 = new ZhimaCustomerCertificationCertifyRequest();

        // 设置业务参数,必须要biz_no
        request1.setBizContent("{\"biz_no\":\"" + bizNo + "\"}");

        // 设置回调地址,必填. 如果需要直接在支付宝APP里面打开回调地址使用alipay协议
        // alipay://www.taobao.com 或者 alipays://www.taobao.com,分别对应http和https请求
        // 设置业务参数,必须要biz_no
        request1.setReturnUrl(returnUrl);

        // 这里一定要使用GET模式
        ZhimaCustomerCertificationCertifyResponse response1;

        try {
            response1 = alipayClient.pageExecute(request1, "GET");
            // 从body中获取URL
            url = response1.getBody();
            System.out.println("generateCertifyUrl url:" + url);
        } catch (AlipayApiException e) {
            throw new BizException("xn798011", "调用芝麻认证时异常");
        }
        return url;
    }

    @Override
    public ZhimaVerifyResult doVerify(AlipayClient alipayClient, String bizNo) {
        ZhimaVerifyResult result = new ZhimaVerifyResult();
        ZhimaCustomerCertificationQueryRequest request = new ZhimaCustomerCertificationQueryRequest();
        request.setBizContent("{\"biz_no\":\"" + bizNo + "\"}");
        ZhimaCustomerCertificationQueryResponse response;
        try {
            response = alipayClient.execute(request);
            result.setErrorCode(response.getCode());
            result.setErrorMsg(response.getMsg());
            if (response.isSuccess()) {
                System.out.println("调用成功");
                if ("true".equals(response.getPassed())) {
                    result.setPassed(true);
                } else if ("false".equals(response.getPassed())) {
                    result.setPassed(false);
                }
            } else {
                System.out.println("调用失败");
                result.setPassed(false);
            }
        } catch (AlipayApiException e) {
            throw new BizException("xn798011", "调用芝麻认证查询时异常");
        }
        return result;
    }
}
