/**
 * @Title FourResult.java 
 * @Package com.std.certi.gateway.res 
 * @Description 
 * @author xieyj  
 * @date 2017年1月3日 下午4:11:54 
 * @version V1.0   
 */
package com.std.certi.gateway.res;

/** 
 * @author: xieyj 
 * @since: 2017年1月3日 下午4:11:54 
 * @history:
 */
public class FourAuthResult {
    // 认证序号
    String oid_authorder;

    // 结果代号
    String ret_code;

    // 结果信息
    String ret_msg;

    // T代表正确，F代表错误
    String result_auth;

    public String getOid_authorder() {
        return oid_authorder;
    }

    public void setOid_authorder(String oid_authorder) {
        this.oid_authorder = oid_authorder;
    }

    public String getRet_code() {
        return ret_code;
    }

    public void setRet_code(String ret_code) {
        this.ret_code = ret_code;
    }

    public String getRet_msg() {
        return ret_msg;
    }

    public void setRet_msg(String ret_msg) {
        this.ret_msg = ret_msg;
    }

    public String getResult_auth() {
        return result_auth;
    }

    public void setResult_auth(String result_auth) {
        this.result_auth = result_auth;
    }

    @Override
    public String toString() {
        return "FourAuthResult [oid_authorder=" + oid_authorder + ", ret_code="
                + ret_code + ", ret_msg=" + ret_msg + ", result_auth="
                + result_auth + "]";
    }
}
