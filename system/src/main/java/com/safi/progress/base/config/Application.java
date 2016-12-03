package com.safi.progress.base.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by safi on 16/12/3.
 */
@RestController
@EnableAutoConfiguration
public class Application {
    @RequestMapping("/")
    String index() {
        return "www.yoodb.com";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
