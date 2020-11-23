package alpos.entity;

import java.io.Serializable;

public class BlackList implements Serializable {
    private Integer id;
    private Integer reviewId;
    private Integer userId;
    private User user;

    public BlackList(){

    }

    public BlackList(Integer id, Integer reviewId) {
        this.id = id;
        this.reviewId = reviewId;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
