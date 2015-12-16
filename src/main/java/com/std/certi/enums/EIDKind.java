package com.std.certi.enums;

public enum EIDKind {
    IDCard("1", "身份证");

    EIDKind(String code, String value) {
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
