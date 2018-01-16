package com.web.station.common;

/**
 * 枚举响应码
 */
public enum ResponseCode {

    SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR"),
    NEED_LOGIN(10, "NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT");

    private final int code;
    private final String describe;

    ResponseCode(int code, String describe){
        this.code = code;
        this.describe = describe;
    }

    public int getCode(){
        return code;
    }

    public String getDescribe(){
        return describe;
    }

}
