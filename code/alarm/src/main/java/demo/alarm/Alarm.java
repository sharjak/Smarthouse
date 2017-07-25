package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Alarm {

    @JsonProperty
    private static String alarming;

    @JsonProperty
    private String ip;

    public Alarm(String alarming){
        this.alarming = alarming;
    }

    public String getAlarming() {
        return alarming;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
