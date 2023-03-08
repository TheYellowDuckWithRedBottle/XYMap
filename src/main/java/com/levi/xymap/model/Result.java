package com.levi.xymap.model;

/**
 * @ClassName
 * @Description TODO
 * @Author LeviFan
 * @Date 2023/3/8 9:42
 * @Version 1.0
 **/
public class Result {
    private int code;
    private String msg;
    private Object data;

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static Result success(Object data){
        Result result = new Result();
        result.setCode(200);
        result.setMsg("success");
        result.setData(data);
        return result;
    }
    public static Result error(){
        Result result = new Result();
        result.setCode(400);
        result.setMsg("error");
        result.setData(null);
        return result;
    }
    public static Result error(String msg){
        Result result = new Result();
        result.setCode(400);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result() {
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
