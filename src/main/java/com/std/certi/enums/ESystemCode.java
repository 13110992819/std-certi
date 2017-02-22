package com.std.certi.enums;

public enum ESystemCode {
    GJS("1", "个金所"), ZHPAY("2", "正汇钱包");

    ESystemCode(String code, String value) {
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
