package com.jvtd.bop2.entity;

import java.util.List;

public class PushAliasReqBean extends BasePushReqBean {
    List<String> targetAlias;

    public List<String> getTargetAlias() {
        return targetAlias;
    }

    public void setTargetAlias(List<String> targetAlias) {
        this.targetAlias = targetAlias;
    }
}
