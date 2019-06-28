package kh.spring.dto;

import org.springframework.web.multipart.MultipartFile;

public class MemberDTO {
	private MultipartFile image;
	private String profilePath;
	private String id;
	private String pw;
	private String email;
	private String phone;
	public MemberDTO() {
		super();
	}
	
	public MemberDTO(MultipartFile image, String profilePath, String id, String pw, String email, String phone) {
		super();
		this.image = image;
		this.profilePath = profilePath;
		this.id = id;
		this.pw = pw;
		this.email = email;
		this.phone = phone;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getProfilePath() {
		return profilePath;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	
	
}
