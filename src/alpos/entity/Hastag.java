package alpos.entity;

import java.io.Serializable;
import java.util.List;

public class Hastag implements Serializable {
    private Integer id;
    private String  name;
    private List<ReviewHastag> reviewHastags;

    public Hastag() {

    }

    public Hastag(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ReviewHastag> getReviewHastags() {
        return reviewHastags;
    }

    public void setReviewHastags(List<ReviewHastag> reviewHastags) {
        this.reviewHastags = reviewHastags;
    }
}
