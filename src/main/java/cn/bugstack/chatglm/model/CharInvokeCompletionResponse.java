package cn.bugstack.chatglm.model;


import cn.bugstack.chatglm.model.invoke.Data;

/**
 * @author jiang_sh
 * @date 30/10/2023
 * @Description
 */
public class CharInvokeCompletionResponse  {
    private int code;
    private String msg;
    private boolean success;
    private Data data;
    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    public boolean getSuccess() {
        return success;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public Data getData() {
        return data;
    }

}
