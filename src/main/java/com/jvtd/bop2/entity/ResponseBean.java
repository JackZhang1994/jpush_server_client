package com.jvtd.bop2.entity;

import com.jvtd.bop2.constant.ResponseConstant;

public class ResponseBean {
    private String code;
    private String msg;

    public ResponseBean(ResponseConstant resultConstant) {
        this.code = resultConstant.getCode();
        this.msg = resultConstant.getMsg();
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
