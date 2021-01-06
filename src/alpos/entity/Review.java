package alpos.entity;

import java.io.Serializable;
import java.util.List;

public class Review implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private String  content;
    private User user;
    private Book book;
    private List<Integer> hastagId;
    private Hastag hastag;
    private List<ReviewHastag> reviewHastags;

    public Hastag getHastag() {
        return hastag;
    }

    public void setHastag(Hastag hastag) {
        this.hastag = hastag;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Review() {
    }

    public Review(Integer id, Integer userId, Integer bookId, List<Integer> hastagId, String content) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ReviewHastag> getReviewHastags() {
        return reviewHastags;
    }

    public void setReviewHastags(List<ReviewHastag> reviewHastags) {
        this.reviewHastags = reviewHastags;
    }

	public List<Integer> getHastagId() {
		return hastagId;
	}

	public void setHastagId(List<Integer> list) {
		this.hastagId = list;
	}
}
