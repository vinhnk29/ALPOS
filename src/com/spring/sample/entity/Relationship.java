package com.spring.sample.entity;

import java.io.Serializable;

public class Relationship implements Serializable {
    private Integer id;
    private Integer followerId;
    private Integer followedId;

    public Relationship() {

    }

    public Relationship(Integer id, Integer followerId, Integer followedId) {
        this.id = id;
        this.followerId = followerId;
        this.followedId = followedId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Integer followerId) {
        this.followerId = followerId;
    }

    public Integer getFollowedId() {
        return followedId;
    }

    public void setFollowedId(Integer followedId) {
        this.followedId = followedId;
    }
}
