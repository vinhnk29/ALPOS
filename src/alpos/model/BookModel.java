package alpos.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class BookModel extends BaseModel {
    private Integer id;
    @NotEmpty(message = "{book.validation.name.required}")
    private String  name;
    @NotNull(message = "{book.validation.author_id.required}")
    private Integer authorId;
    @NotNull(message = "{book.validation.publisher_id.required}")
    private Integer publisherId;
    @NotNull(message = "{book.validation.category_id.required}")
    private Integer categoryId;
    private Integer releaseYear;

    public BookModel() {

    }

    public BookModel(Integer id, String name, @NotNull(message = "{book.validation.author_id.required}") Integer authorId, @NotNull(message = "{book.validation.publisher_id.required}") Integer publisherId, @NotNull(message = "{book.validation.category_id.required}") Integer categoryId, Integer releaseYear) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.publisherId = publisherId;
        this.categoryId = categoryId;
        this.releaseYear = releaseYear;
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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }
}
