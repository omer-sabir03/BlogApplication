package payload;

import java.util.Date;


public class PostDTO {
		
	private String postId;
	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	private String postTitle;
	private String postContent;
	private String imageName;
	private Date addDate;
	
	private CategoryDTO categoryDTO;
	
	private UserDTO userDTO;

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public PostDTO(String postTitle, String postContent, String imageName, Date addDate, CategoryDTO categoryDTO,
			UserDTO userDTO) {
		super();
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.imageName = imageName;
		this.addDate = addDate;
		this.categoryDTO = categoryDTO;
		this.userDTO = userDTO;
	}
	
	public PostDTO() {
		super();		
	}
	
	
}
