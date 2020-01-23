package com.example.bawei.homemodule.bean;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/21 17:19
 * @Email 1151403054@qq.com
 */
public class MySaveRe {

    /**
     * code : 200
     * data : true
     * msg : 操作成功
     */

    private int code;
    private boolean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
