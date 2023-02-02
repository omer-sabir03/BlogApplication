package payload;

public class CommentDTO {
		
	private Integer commentId;
	private String content;
	private PostDTO postDto;
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public PostDTO getPostDto() {
		return postDto;
	}
	public void setPostDto(PostDTO postDto) {
		this.postDto = postDto;
	}
	public CommentDTO(Integer commentId, String content, PostDTO postDto) {
		super();
		this.commentId = commentId;
		this.content = content;
		this.postDto = postDto;
	}
	
	public CommentDTO() {
		super();
	}
	
	
	
}
