package com.std.certi.enums;

public enum EZhimaCode {
    // 三方解析
    // 10000 接口调用成功
    // 20000 服务不可用
    // 20001 授权权限不足
    // 40001 缺少必选参数
    // 40002 非法的参数
    // 40004 业务处理失败
    // 40006 权限不足
    Pass("10000", "接口调用成功");
    EZhimaCode(String code, String value) {
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
