package com.web.station.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * 服务响应类
 * @param <T>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL) //保证如果序列化是null的对象，就会消失
public class ServerResponse<T> implements Serializable {

    private int status;
    private String msg;
    private T data;

    //私有化构造方法
    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }


    //忽略不序列化
    @JsonIgnore
    public boolean isSuccess(){
        return this.status ==ResponseCode.SUCCESS.getCode();
    }


    public int getStatus() {
        return status;
    }


    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    //成功
    //返回成功的状态码
    public static <T> ServerResponse createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    //返回成功的状态码和数据
    public static <T> ServerResponse createBySuccess(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    //返回成功状态码、描述信息、数据
    public static <T> ServerResponse createBySuccess(String msg, T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    //返回成功状态码、描述信息
    public static <T> ServerResponse createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg);
    }

    //失败
    // 返回失败的状态码、默认错误信息
    public static <T> ServerResponse createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDescribe());
    }

    //返回失败的状态码、定义的错误信息
    public static <T> ServerResponse createByErrorMessage(String errorMsg){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), errorMsg);
    }

    //返回定义的失败状态码、定义的错误信息
    public static <T> ServerResponse createByErrorCodeMessage(int errorCode, String errorMsg){
        return new ServerResponse<T>(errorCode, errorMsg);
    }
}
