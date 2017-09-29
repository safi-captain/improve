package com.safi.dairy.biz.dao;

import com.safi.dairy.biz.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by safi on 17/4/8.
 */
@Service
public interface UserDao {
    List<User> getAllUsers();
}
