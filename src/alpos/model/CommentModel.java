package alpos.model;

import javax.validation.constraints.NotEmpty;

public class CommentModel extends BaseModel {
	private Integer id;
	private Integer reviewId;
	private Integer userId;
	@NotEmpty(message = "{comment.validation.content.required}")
	private String content;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
