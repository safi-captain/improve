package com.safi.micro.remote;

import com.safi.micro.entity.User;
import com.safi.micro.hystrix.HystrixClientFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microservice-provider-user", fallback = HystrixClientFallback.class)
public interface UserFeignClient {
    @RequestMapping("/{id}")
    public User findByIdFeign(@RequestParam("id") Long id);
}
