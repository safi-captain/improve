package com.safi.dairy.biz.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by safi on 17/8/4.
 */
@Data
public class SchoolCollege {
    private School school;
    private List<College> colleges;
}
