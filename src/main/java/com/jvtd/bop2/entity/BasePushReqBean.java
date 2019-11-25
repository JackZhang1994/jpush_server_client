package com.jvtd.bop2.entity;

import java.util.Map;

public class BasePushReqBean {
    boolean apnsProduction;
    long timeToAlive;
    String title;
    String content;
    Map<String, String> extras;

    public boolean isApnsProduction() {
        return apnsProduction;
    }

    public void setApnsProduction(boolean apnsProduction) {
        this.apnsProduction = apnsProduction;
    }

    public long getTimeToAlive() {
        return timeToAlive;
    }

    public void setTimeToAlive(long timeToAlive) {
        this.timeToAlive = timeToAlive;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, String> extras) {
        this.extras = extras;
    }
}
