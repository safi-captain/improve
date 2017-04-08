package com.safi.progress.biz.memberIndex.domain;

import lombok.Data;

/**
 * Created by safi on 16/12/19.
 */
@Data
public class Member {
    private String userId;
    private String memberId;
    private String memberName;
    private int newMessage;
    private int newSystemMessage;
}
