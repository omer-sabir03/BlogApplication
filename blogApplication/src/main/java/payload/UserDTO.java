package payload;

import javax.validation.constraints.Email; 
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDTO {
	
	private  int id;
	
	@NotNull
	@Size(min=3 ,message="Name should be minimum of 3 letters long")
	private String name;
	
	@Email(message="Enter The Valid Email address")
	@NotEmpty
	private String email;
	
	@NotEmpty( message="Password Length must be min 8 characters")
	@Size(min=8 ,max=16)
	private String password;
	
	@javax.validation.constraints.NotEmpty
	private String about;
	
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public UserDTO() {
		super();
		
	}
}
