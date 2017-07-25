package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import com.example.demo.Temperature;

@RestController
public class TemperatureController {

    @RequestMapping(value = "/temperature", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Temperature temperature(){
        Temperature temperature = new Temperature("21.5");
        try {
            InetAddress addr = InetAddress.getLocalHost();
            String ip = addr.getHostAddress();
            temperature.setIp(ip);
            return temperature;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return temperature;
        }
    }
}