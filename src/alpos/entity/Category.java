package alpos.entity;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {
    private Integer id;
    private String  name;
    private List<Book> books;

	public Category() {

    }

    public Category(Integer id, String name) {
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
    public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
