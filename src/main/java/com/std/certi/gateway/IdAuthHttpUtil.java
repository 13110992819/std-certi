package com.std.certi.gateway;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.std.certi.domain.VerifyResult;

public class IdAuthHttpUtil {
    static Logger logger = Logger.getLogger(IdAuthHttpUtil.class);

    private static final String IDAUTH_URL = "http://121.40.136.150:8080/IdInDataAu/api/spAuthenInfoApi.htm";

    // private static final String IDBALANCE_URL =
    // "http://121.40.136.150:8080/IdInDataAu/api/queryActBalance.htm";

    private static final String USERID = "100218";

    private static final String DESKEY = "753v7vM51m9dSfWZvZcv711c3bWH9B9s";

    private static final String MD5KEY = "1TM31s33fPtks5NdJ1F59k7d3hn9Pc3T";

    public static void main(String[] args) throws Exception {

        String xml = sendIdAuthPost("431023198108234231", "吴熙瑞");
        VerifyResult result = getIdAuthVerifyMap(xml);
        System.out.println(result);

    }

    public static VerifyResult httpParse(String idNo, String realName)
            throws Exception {
        String xml = sendIdAuthPost(idNo, realName);
        return getIdAuthVerifyMap(xml);

    }

    /**
     *  
     * @param backHtml
     * @return
     * @throws Exception 
     * @create: 2015年12月4日 下午1:56:33 myb858
     * @history:
     */
    public static VerifyResult getIdAuthVerifyMap(String backHtml)
            throws Exception {
        // <?xml version="1.0" encoding="GBK" ?>
        // <response>
        // <userId>100218</userId>
        // <coopOrderNo></coopOrderNo>
        // <auOrderNo>101302012</auOrderNo>
        // <auResultCode>SUCCESS</auResultCode>
        // <auResultInfo>一致</auResultInfo>
        // <auSuccessTime>2015-10-20 18:08:45</auSuccessTime>
        // <img></img>
        // <errorCode>9013</errorCode>
        // <errorMsg>一致</errorMsg>
        // </response>
        VerifyResult result = new VerifyResult();

        String errorCode = null;
        Pattern pattern = Pattern.compile("<errorCode>(.*)</errorCode>");
        Matcher matcher = pattern.matcher(backHtml);
        while (matcher.find()) {
            errorCode = matcher.group(1);
        }
        result.setErrorCode(errorCode);

        String errorMsg = null;
        pattern = Pattern.compile("<errorMsg>(.*)</errorMsg>");
        matcher = pattern.matcher(backHtml);
        while (matcher.find()) {
            errorMsg = matcher.group(1);
        }
        result.setErrorMsg(errorMsg);
        return result;

    }

    public static String sendIdAuthPost(String idNo, String realName)
            throws Exception {
        String resultMsg = "";
        String urlStr = IDAUTH_URL;
        // http://121.40.136.150:8080/IdInDataAu/api/spAuthenInfoApi.htm?userId=200000&coopOrderNo=123456789&auName=lishi&auId=312743198811110000&reqDate=2014-01-01
        // 12:23:23&ts=122222121&uImage=xx&authType=xx&bankCardNo=xx&sign=xxxxxxxxxx
        String userId = USERID;
        String ts = System.currentTimeMillis() + "";
        // String orderNo =ts;
        String mKey = MD5KEY;// MD5密钥
        String dateString = "";
        // strDefaultKey="DES密钥";//DES密钥
        SendAuthReqUtil.setStrDefaultKey(DESKEY);
        try {
            realName = URLEncoder.encode(realName, "UTF-8");
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
            dateString = formatter.format(currentTime);
            dateString = URLEncoder.encode(dateString, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw e;
        }
        String nameDes = SendAuthReqUtil.Encode(realName);
        String idCodeDes = SendAuthReqUtil.Encode(idNo);
        String md5Str = "userId" + userId + "auName" + realName + "auId" + idNo
                + "ts" + ts + mKey;
        md5Str = Md5Util.md5Sign(md5Str);
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", userId);
        params.put("coopOrderNo", null);
        params.put("auName", nameDes);
        params.put("auId", idCodeDes);
        params.put("reqDate", dateString);
        params.put("ts", ts);
        params.put("sign", md5Str);
        params.put("authType", "01");// 01：实名认证，02：人证合一，03：银行卡认证
        params.put("uImage", "");
        params.put("bankCardNo", "");

        try {
            logger.debug(urlStr + "?userId=" + userId + "&coopOrderNo=&auName="
                    + nameDes + "&auId=" + idCodeDes + "&reqDate=" + dateString
                    + "&ts=" + ts + "&sign=" + md5Str);
            resultMsg = SendAuthReqUtil.sendHttpRequest(urlStr, params);
            logger.debug(resultMsg);
        } catch (Exception e) {
            throw e;
        }
        return resultMsg;
    }

}
