package com.spring.sample.entity;

import java.io.Serializable;

public class ReviewComment implements Serializable {
    private Integer id;
    private Integer reviewId;
    private String  content;

    public ReviewComment() {

    }

    public ReviewComment(Integer id, Integer reviewId, String content) {
        this.id = id;
        this.reviewId = reviewId;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
