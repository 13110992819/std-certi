package com.std.certi.enums;

public enum EVerifyCode {

    // 三方解析
    // 9000 请求成功
    // 9001 数据校验(MD5)失败
    // 9002 缺少必要参数
    // 9003 IP校验非法
    // 9004 检测账户不存在
    // 9005 检测账户状态非法
    // 9006 检测账户权限不足
    // 9007 验证身份证号码不合法
    // 9008 照片数据过大
    // 9009 检测账户余额不足
    // 9010 身份证号:一致姓名:一致
    // 9011 身份证号:一致姓名:不一致
    // 9012 库无记录
    // 9013 一致
    // 9014 不一致
    // 9015 核验失败，请稍候再试
    // 9016 数据校验(DES)失败
    // 9999 系统服务异常
    Pass("9013", "验证通过");
    EVerifyCode(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
