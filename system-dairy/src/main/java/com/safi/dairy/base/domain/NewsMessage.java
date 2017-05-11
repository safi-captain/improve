package com.safi.dairy.base.domain;

import lombok.Data;

import java.util.List;

@Data
public class NewsMessage extends BaseMessage{
    private int ArticleCount;
    private List<News> Articles;
}
