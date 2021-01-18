package alpos.model;

import java.util.List;

public class ReviewModel extends BaseModel{
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private List<Integer> hastagId;
    private String  content;
    private UserModel user;
    private BookModel book;
    private HastagModel hastag;
    private List<ReviewHastagModel> reviewHastags;
    private List<HastagModel> hastagModels;


    public List<Integer> getHastagId() {
        return hastagId;
    }

    public void setHastagId(List<Integer> hastagId) {
        this.hastagId = hastagId;
    }

    public ReviewModel(Integer id, Integer userId, Integer bookId, List<Integer> hastagId, String content) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.hastagId = hastagId;
        this.content = content;
    }

    public HastagModel getHastag() {
        return hastag;
    }

    public void setHastag(HastagModel hastag) {
        this.hastag = hastag;
    }

    public BookModel getBook() {
        return book;
    }

    public void setBook(BookModel book) {
        this.book = book;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public ReviewModel(){

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ReviewHastagModel> getReviewHastags() {
        return reviewHastags;
    }

    public void setReviewHastags(List<ReviewHastagModel> reviewHastags) {
        this.reviewHastags = reviewHastags;
    }

    public List<HastagModel> getHastagModels() {
        return hastagModels;
    }

    public void setHastagModels(List<HastagModel> hastagModels) {
        this.hastagModels = hastagModels;
    }
}
