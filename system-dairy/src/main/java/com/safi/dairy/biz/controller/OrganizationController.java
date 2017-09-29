package com.safi.dairy.biz.controller;

import com.safi.dairy.biz.dao.OrganizationDao;
import com.safi.dairy.biz.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by safi on 17/7/16.
 */
@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    OrganizationDao organizationDao;

    @RequestMapping("/query-school-detail/{schoolId}")
    public SchoolDetail querySchoolDetail(@PathVariable String schoolId) {
        School school = organizationDao.querySchoolDetail(schoolId);
        List<Image> images = organizationDao.queryAimImgByCollegeId(schoolId);
        SchoolDetail schoolDetail = new SchoolDetail();
        schoolDetail.setSchool(school);
        schoolDetail.setImages(images);
        return schoolDetail;
    }

    @RequestMapping("/query-colleges/{schoolId}")
    public SchoolCollege queryColleges(@PathVariable String schoolId){
        School school = organizationDao.querySchoolDetail(schoolId);
        List<College> colleges = organizationDao.queryAimColleges(schoolId);
        SchoolCollege schoolCollege = new SchoolCollege();
        schoolCollege.setColleges(colleges);
        schoolCollege.setSchool(school);
        return schoolCollege;
    }

    @RequestMapping("/query-college-detail/{collegeId}")
    public CollegeDetail queryCollegeDetail(@PathVariable String collegeId) {
        College college = organizationDao.queryAimCollege(collegeId);
        List<QQ> qqList = organizationDao.queryAimQQByCollegeId(collegeId);
        CollegeDetail collegeDetail = new CollegeDetail();
        collegeDetail.setCollege(college);
        collegeDetail.setQqList(qqList);
        return collegeDetail;
    }
}
