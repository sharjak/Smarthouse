package demo.app;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ApplicationController {

    RestTemplate restTemplate;

    @Autowired
    public ApplicationController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${demo.alarm.alarmResource}")
    String alarmResource;

    @Value("${demo.temp.tempResource}")
    String tempResource;

    @RequestMapping(value = "/smarthouse", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> alarm() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(alarmResource, String.class);
        return response;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> login() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(alarmResource, String.class);
        return response;
    }

    @RequestMapping(value = "/smarthouse2", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> temperature() throws Exception {
        ResponseEntity<String> response2 = restTemplate.getForEntity(tempResource, String.class);
        return response2;
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> listUser() {
        ObjectMapper mapper = new ObjectMapper();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        try {
            String jsonInString = mapper.writeValueAsString(principal);
            return new ResponseEntity<String>(jsonInString, HttpStatus.OK);
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("FAILED", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/")
    public ResponseEntity<String> smarthouse(){
        return new ResponseEntity<String>("Welcome to Smarthouse", HttpStatus.OK);
    }
}
