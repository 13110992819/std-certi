package com.std.certi.gateway;

import org.apache.log4j.Logger;

import com.std.certi.common.PropertiesUtil;
import com.std.certi.core.HttpClientAccessTool;
import com.std.certi.core.JsonUtils;
import com.std.certi.domain.VerifyResult;
import com.std.certi.gateway.res.FourAuthResult;

public class FourIdAuthHttpUtil {
    static Logger logger = Logger.getLogger(FourIdAuthHttpUtil.class);

    private static final String IDAUTH_URL = "http://125.77.22.244:1285/Thirdparty/AuthServ";

    public static void main(String[] args) throws Exception {
        try {
            String authType = "bankcardAuth";
            String idNo = "123456";
            String idName = "谢延径";
            String mobileNo = "";
            String cardNo = "6217231202001071646";
            String sendUrl = IDAUTH_URL + "?authType=" + authType + "&id_no="
                    + idNo + "&id_name=" + idName + "&mobile_no=" + mobileNo
                    + "&card_no=" + cardNo + "&auth_key="
                    + PropertiesUtil.Config.AUTH_KEY;
            logger.debug(sendUrl);
            String backEncodType = "utf-8";
            String resultMsg = HttpClientAccessTool.doAccessHTTPGet(sendUrl,
                backEncodType);
            System.out.println("resultMsg:" + resultMsg);
            FourAuthResult fourAuthResult = JsonUtils.json2Bean(resultMsg,
                FourAuthResult.class);
            logger.debug(fourAuthResult.toString());
        } catch (Exception e) {
            throw e;
        }
    }

    public static VerifyResult httpParse(String idNo, String realName,
            String cardNo, String bindMobile) throws Exception {
        String authType = "bankcardAuth";
        String sendUrl = IDAUTH_URL + "?authType=" + authType + "&id_no="
                + idNo + "&id_name=" + realName + "&mobile_no=" + bindMobile
                + "&card_no=" + cardNo + "&auth_key="
                + PropertiesUtil.Config.AUTH_KEY;
        System.out.println("sendUrl:" + sendUrl);
        String backEncodType = "utf-8";
        String resultMsg = HttpClientAccessTool.doAccessHTTPGet(sendUrl,
            backEncodType);
        System.out.println("resultMsg:" + resultMsg);
        FourAuthResult fourAuthResult = JsonUtils.json2Bean(resultMsg,
            FourAuthResult.class);
        VerifyResult result = new VerifyResult(fourAuthResult.getRet_code(),
            fourAuthResult.getRet_msg());
        return result;
    }
}
