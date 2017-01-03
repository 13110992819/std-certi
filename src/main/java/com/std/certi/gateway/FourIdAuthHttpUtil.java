package com.std.certi.gateway;

import org.apache.log4j.Logger;

import com.std.certi.core.HttpClientAccessTool;
import com.std.certi.core.JsonUtils;
import com.std.certi.domain.VerifyResult;
import com.std.certi.enums.EFourVerifyCode;
import com.std.certi.gateway.res.FourAuthResult;

public class FourIdAuthHttpUtil {
    static Logger logger = Logger.getLogger(FourIdAuthHttpUtil.class);

    private static final String IDAUTH_URL = "http://125.77.22.244:1285/Thirdparty/AuthServ";

    public static void main(String[] args) throws Exception {
        try {
            String authType = "bankcardAuth";
            String idNo = "330326199007015211";
            String idName = "谢延径";
            String mobileNo = "18767101909";
            String cardNo = "6217231202001071643";
            String sendUrl = IDAUTH_URL + "?authType=" + authType + "&id_no="
                    + idNo + "&id_name=" + idName + "&mobile_no=" + mobileNo
                    + "&card_no=" + cardNo;
            logger.debug(sendUrl);
            String backEncodType = "utf-8";
            String resultMsg = HttpClientAccessTool.doAccessHTTPGet(sendUrl,
                backEncodType);
            logger.debug("resultMsg:" + resultMsg);
            FourAuthResult fourAuthResult = JsonUtils.json2Bean(resultMsg,
                FourAuthResult.class);
            logger.debug(fourAuthResult.toString());
        } catch (Exception e) {
            throw e;
        }
    }

    public static VerifyResult httpParse(String idNo, String realName,
            String cardNo, String bindMobile) throws Exception {
        VerifyResult result = new VerifyResult();
        result.setErrorCode(EFourVerifyCode.Pass.getCode());
        result.setErrorMsg(EFourVerifyCode.Pass.getValue());
        // String xml = sendIdAuthPost(idNo, realName);
        // return getIdAuthVerifyMap(xml);
        return result;

    }
}
