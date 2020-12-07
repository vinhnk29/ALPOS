package alpos.model;

import alpos.entity.User;

public class ReviewModel extends BaseModel{

    private Integer id;
    private Integer userId;
    private Integer bookId;
    private Integer hastagId;
    private String  content;
    private UserModel user;
    private BookModel book;

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

    public ReviewModel(Integer id, Integer userId, Integer bookId, Integer hastagId, String content) {
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
