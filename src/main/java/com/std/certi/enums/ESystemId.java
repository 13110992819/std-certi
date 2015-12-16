package com.std.certi.enums;

public enum ESystemId {
    P2P("1", "P2P"), CPZC("2", "产品众筹");

    ESystemId(String code, String value) {
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
