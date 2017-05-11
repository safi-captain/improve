package com.safi.dairy.base.domain;

import lombok.Data;

@Data
public class BaseMessage {
    private String ToUserName;
    private String FromUserName;
    private Long CreateTime;
    private String MsgType;
}
