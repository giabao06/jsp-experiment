package xyz.giabao06.jwtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication

public class TestApp {
    @RequestMapping("/")
    String home(){
        return "Hello world!";
    }

    @RequestMapping("/testPage")
    String testPage(){
        return "This is a test page";
    }

    public static void main(String[] args){
        SpringApplication.run(TestApp.class, args);
    }
}
