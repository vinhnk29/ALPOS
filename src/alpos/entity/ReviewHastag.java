package alpos.entity;

import java.io.Serializable;

public class ReviewHastag implements Serializable {
    private Integer id;
    private Integer reviewId;
    private Integer hastagId;
    private Hastag  hastag;
    private Review  review;

    public ReviewHastag() {

    }

    public ReviewHastag(Integer id, Integer reviewId, Integer hastagId) {
        this.id = id;
        this.reviewId = reviewId;
        this.hastagId = hastagId;
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

    public Integer getHastagId() {
        return hastagId;
    }

    public void setHastagId(Integer hastagId) {
        this.hastagId = hastagId;
    }

    public Hastag getHastag() {
        return hastag;
    }

    public void setHastag(Hastag hastag) {
        this.hastag = hastag;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
