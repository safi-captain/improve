package com.safi.progress.base.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by safi on 16/12/3.
 */
@Controller
@EnableAutoConfiguration
public class Application {
    @RequestMapping("/")
    String index() {
        return "index";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
