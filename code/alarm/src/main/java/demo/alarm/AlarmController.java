package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import com.example.demo.Alarm;

@RestController
public class AlarmController {

    @RequestMapping(value = "/alarm", method = RequestMethod.GET, produces = "application/json")
    public Alarm alarm(){
        Alarm alarm = new Alarm("on");
        try {
            InetAddress addr = InetAddress.getLocalHost();
            String ip = addr.getHostAddress();
            alarm.setIp(ip);
            return alarm;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return alarm;
        }
    }
}