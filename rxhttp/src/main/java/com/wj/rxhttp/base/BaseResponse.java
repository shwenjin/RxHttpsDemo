package com.wj.rxhttp.base;

/**
 * Created by wenjin on 2017/7/3.
 * 请求结果基类
 */

public class BaseResponse {
    private int code; //错误码
    private String msg;//错误描述

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
