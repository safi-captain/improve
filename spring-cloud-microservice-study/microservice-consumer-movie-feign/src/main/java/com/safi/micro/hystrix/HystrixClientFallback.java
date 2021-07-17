package com.safi.micro.hystrix;

import com.safi.micro.entity.User;
import com.safi.micro.remote.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HystrixClientFallback implements UserFeignClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(HystrixClientFallback.class);

    @Override
    public User findByIdFeign(Long id) {
        LOGGER.info("异常发生，进入fallback方法，接收的参数：id = {}", id);
        User user = new User();
        user.setId(-1L);
        user.setUsername("default username");
        user.setAge(0);
        return user;
    }
}
