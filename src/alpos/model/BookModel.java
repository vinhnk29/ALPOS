package alpos.model;

import alpos.entity.Author;
import alpos.entity.Category;
import alpos.entity.Publisher;
import alpos.uploader.ImageUpload;
import alpos.uploader.cloudinary.CloudinaryImageUpload;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    private MultipartFile file;

    private PublisherModel publisher;
    private AuthorModel  author;
    private CategoryModel category;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

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

    public boolean isAttached() {
        return StringUtils.hasText(image);
    }

    public ImageUpload getUpload() {
        ImageUpload file = new CloudinaryImageUpload();
        if (StringUtils.hasText(image)) {
            file.setStoredPath(image);
        }
        return file;
    }

    public void setUpload(ImageUpload file) {
        this.image = file.getStoredPath();
    }


}

