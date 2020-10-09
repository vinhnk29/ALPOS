package com.spring.sample.entity;

import java.io.Serializable;

public class Review implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private Integer hastagId;
    private String  content;

    public Review() {
    }

    public Review(Integer id, Integer userId, Integer bookId, Integer hastagId, String content) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.hastagId = hastagId;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getHastagId() {
        return hastagId;
    }

    public void setHastagId(Integer hastagId) {
        this.hastagId = hastagId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
