package com.wj.rxhttp.exception;

/**
 * Created by wenjin on 2017/7/3.
 */

public class ApiException {
    public static final String errorMsg_SocketTimeoutException = "网络链接超时，请检查您的网络状态，稍后重试！";
    public static final String errorMsg_ConnectException = "网络链接异常，请检查您的网络状态";
    public static final String errorMsg_UnknownHostException = "网络异常，请检查您的网络状态";
    public static final String errorMsg_HTTPExceprion_504 = "网关超时";

    public static String httpCode(int code){
        String msg="网络错误";
        switch (code){
            case 504:
                msg=errorMsg_HTTPExceprion_504;
        }
        return msg;
    }
}
