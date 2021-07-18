package com.safi.micro.controller;

import com.safi.micro.entity.User;
import com.safi.micro.remote.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class FeignController {
    private static final Logger log = LoggerFactory.getLogger(FeignController.class);
    @Autowired
    private UserFeignClient userFeignClient;
    @Value("${school}")
    private String school;

    @GetMapping("feign/{id}")
    public User findByIdFeign(@PathVariable Long id) {
        log.info(school);
        User user = this.userFeignClient.findByIdFeign(id);
        return user;
    }
}
