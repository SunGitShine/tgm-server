package com.juma.tgm.common;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghang
 * Date: 2016/5/10
 * Time: 16:53
 * To change this template use File | Settings | File Templates.
 */
public class Result extends LinkedHashMap<String, Object> implements Serializable{
    private static final long serialVersionUID = 5764708053187525086L;

    public static final String SUCCESS = "success";

    private boolean success=true;
    private String message;

    public Result() {
        this.add("success", success);
        this.add("message", message);
    }

    public void add(String key, Object value){
        this.put(key, value);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
