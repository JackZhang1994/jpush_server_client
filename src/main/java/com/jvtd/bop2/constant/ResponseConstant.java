package com.jvtd.bop2.constant;

public enum ResponseConstant {
    SUCCESS("0000", "操作成功"),
    FAILED("9000", "操作失败"),
    SYSTEM_EXCEPTION("9001", "系统错误");

    public String code;
    public String msg;

    private ResponseConstant(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
