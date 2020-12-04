package alpos.model;

import java.util.List;

public class AuthorModel extends BaseModel {
    private Integer id;
    private String  name;
    private List<BookModel> books;

    public List<BookModel> getBooks() {
        return books;
    }

    public void setBooks(List<BookModel> books) {
        this.books = books;
    }

    public AuthorModel(){

    }

    public AuthorModel(Integer id, String name) {
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
}
