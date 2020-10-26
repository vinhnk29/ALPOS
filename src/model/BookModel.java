package model;

public class BookModel {
    private Integer id;
    private String  name;
    private AuthorModel author;
    private PublisherModel publisher;
    private CategoryModel category;
    private Integer    releaseYear;

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
	public AuthorModel getAuthor() {
		return author;
	}
	public void setAuthor(AuthorModel author) {
		this.author = author;
	}
	public PublisherModel getPublisher() {
		return publisher;
	}
	public void setPublisher(PublisherModel publisher) {
		this.publisher = publisher;
	}
	public CategoryModel getCategory() {
		return category;
	}
	public void setCategory(CategoryModel category) {
		this.category = category;
	}
	public Integer getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

}
