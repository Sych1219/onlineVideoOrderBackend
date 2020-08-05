package net.xdclass.demo.utils;

public class JsonData {

    private Integer code;
    private String msg;
    private Object data;

    public static JsonData buildSuccess(){
        return new JsonData(0,null,null);
    }

    public static JsonData buildSuccess(Object data){
        return new JsonData(0,null,data);
    }

    public static JsonData buildError(String  msg){
        return new JsonData(-1 ,null,msg);
    }

    public static JsonData buildError(Integer code , String  msg){
        return new JsonData(code ,null,msg);
    }

    public JsonData(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
