package com.safi.dairy.biz.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by safi on 17/7/16.
 */
@Data
public class CollegeDetail {
    private College college;
    private List<QQ> qqList;
}
