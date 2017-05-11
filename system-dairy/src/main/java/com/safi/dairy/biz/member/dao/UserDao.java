package com.safi.dairy.biz.member.dao;

import com.safi.dairy.biz.member.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by safi on 17/4/8.
 */
@Service
public interface UserDao {
    List<User> getAllUsers();
}
