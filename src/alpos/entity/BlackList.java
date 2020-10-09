package alpos.entity;

import java.io.Serializable;

public class BlackList implements Serializable {
    private Integer id;
    private Integer reviewId;

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
}
