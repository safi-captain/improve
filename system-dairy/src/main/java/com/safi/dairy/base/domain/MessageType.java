package com.safi.dairy.base.domain;

public enum  MessageType {
    TEXT("TEXT",0),
    NEWS("NEWS",1),
    IMAGE("IMAGE",2),
    VOICE("VOICE",3),
    VIDEO("VIDEO",4),
    LINK("LINK",5),
    LOCATION("LOCATION",6),
    EVENT("EVENT",7),
    SUBSCRIBE("SUBSCRIBE",8),
    UNSUBSCRIBE("UNSUBSCRIBE",9),
    CLICK("CLICK",10),
    VIEW("VIEW",11);

    private String key;
    private int value;

    MessageType(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
