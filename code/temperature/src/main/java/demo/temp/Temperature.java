package com.example.demo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Temperature {

    @JsonProperty
    private static String temperature;
    @JsonProperty
    private String ip;

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Temperature(String temperature){
        this.temperature = temperature;
    }

    public String getTemperatures() {
        return temperature;
    }
}
