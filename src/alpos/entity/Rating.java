package alpos.entity;

import java.io.Serializable;

public class Rating implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private Integer point;

    public Rating() {

    }

    public Rating(Integer id, Integer userId, Integer bookId, Integer point) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.point = point;
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

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
