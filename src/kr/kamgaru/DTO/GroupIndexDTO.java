package kr.kamgaru.DTO;

public class GroupIndexDTO {
	private String groupname;
	private String title;
	private String contents;
	private String hit;
	private String image;
	private String enddate;
	
	public GroupIndexDTO(){}
	
	public GroupIndexDTO(String groupname, String title, String contents, String hit,
			String image, String enddate) {
		super();
		this.groupname = groupname;
		this.title = title;
		this.contents = contents;
		this.hit = hit;
		this.image = image;
		this.enddate = enddate;
	}

	public String getTitle() {
		return title;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
	
}
