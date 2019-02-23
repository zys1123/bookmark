package com.bookmark.entity.response;

/**
 * Created by 12425 on 2018/8/8.
 */
public class ResponseData{

    private String code;

    private String msg;

    private Object data;

    public ResponseData(String code,String msg,Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
