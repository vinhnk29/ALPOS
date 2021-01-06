package alpos.model;


public class ReviewHastagModel extends BaseModel {
    private Integer id;
    private Integer reviewId;
    private Integer hastagId;
    private HastagModel hastagModel;
    private ReviewModel reviewModel;

    public ReviewHastagModel(){

    }

    public ReviewHastagModel(Integer id, Integer reviewId, Integer hastagId) {
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

    public HastagModel getHastagModel() {
        return hastagModel;
    }

    public void setHastagModel(HastagModel hastagModel) {
        this.hastagModel = hastagModel;
    }

    public ReviewModel getReviewModel() {
        return reviewModel;
    }

    public void setReviewModel(ReviewModel reviewModel) {
        this.reviewModel = reviewModel;
    }
}
