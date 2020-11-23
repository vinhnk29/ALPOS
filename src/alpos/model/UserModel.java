package alpos.model;

import alpos.uploader.ImageUpload;
import alpos.uploader.cloudinary.CloudinaryImageUpload;
import alpos.validator.FieldMatch;
import alpos.validator.NullOrNotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch.List({
		@FieldMatch(first = "password", second = "confirmation", message = "{user.validation.password.notmatch}") })
public class UserModel {

	private Integer id;
	@NotEmpty(message = "{user.validation.name.required}")
	@Size(max = 64, message = "{user.validation.name.max}")
	private String name;
	@NotNull(message = "{user.validation.date.required}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	@Email(message = "{pattern.email}")
	private String email;
	@NullOrNotBlank(message = "{user.validation.password.required}")
	@Size(max = 64, min = 6, message = "{user.validation.password.length}")
	private String password;
	@NullOrNotBlank(message = "{user.validation.confirmation.required}")
	private String confirmation = null;
	private Long reviewNumbers = null;
	private Long followers = null;
	private Long followings = null;

	private MultipartFile file;
	private String image;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public UserModel() {

	}

	public UserModel(Integer id, String name, String email, String password, String confirmation) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirmation = confirmation;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	public Long getReviewNumbers() {
		return reviewNumbers;
	}

	public void setReviewNumbers(Long reviewNumbers) {
		this.reviewNumbers = reviewNumbers;
	}

	public Long getFollowers() {
		return followers;
	}

	public void setFollowers(Long followers) {
		this.followers = followers;
	}

	public Long getFollowings() {
		return followings;
	}

	public void setFollowings(Long followings) {
		this.followings = followings;
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
