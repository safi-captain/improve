package com.safi.dairy.biz.controller;

import com.safi.dairy.biz.dao.OrganizationDao;
import com.safi.dairy.biz.dao.UserDao;
import com.safi.dairy.biz.domain.School;
import com.safi.dairy.biz.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by safi on 17/5/31.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    OrganizationDao organizationDao;

    @RequestMapping("/init")
    public User init(){
        User user = userDao.getAllUsers().get(0);
//        User user = new User();
//        user.setUserId("12345678910");
//        user.setUserName("safi");
        return user;
    }

    @RequestMapping("/get-school/{schoolId}")
    public School getSchool(@PathVariable String schoolId){
        School school = organizationDao.querySchoolDetail(schoolId);
        return school;
    }
}
