package alpos.entity;

import java.io.Serializable;

public class Comment implements Serializable {
    private Integer id;
    
    private Integer reviewId;
    private Integer userId;
    private String content;
    private User user;
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

    public Comment() {

    }

    public Comment(Integer id, Integer reviewId, Integer userId, String content, User user) {
        this.id = id;
        this.reviewId = reviewId;
        this.userId = userId;
        this.content = content;
        this.user = user;
    }
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}