package com.safi.dairy.biz.dao;

import com.safi.dairy.biz.domain.College;
import com.safi.dairy.biz.domain.Image;
import com.safi.dairy.biz.domain.QQ;
import com.safi.dairy.biz.domain.School;

import java.util.List;

/**
 * Created by safi on 17/7/16.
 */
public interface OrganizationDao {
    School querySchoolDetail(String schoolId);

    List<College> queryAimColleges(String schoolId);

    College queryAimCollege(String collegeId);

    List<QQ> queryAimQQByCollegeId(String collegeId);

    List<Image> queryAimImgByCollegeId(String collegeId);
}
