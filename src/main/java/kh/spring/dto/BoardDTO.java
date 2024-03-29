package kh.spring.dto;

public class BoardDTO {
	private int bNo;
	private String title;
	private String imagePath;
	private String contents;
	private String writer;
	
	public BoardDTO() {
		super();
	}

	public BoardDTO(int bNo, String title, String imagePath, String contents, String writer) {
		super();
		this.bNo = bNo;
		this.title = title;
		this.imagePath = imagePath;
		this.contents = contents;
		this.writer = writer;
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	
}
