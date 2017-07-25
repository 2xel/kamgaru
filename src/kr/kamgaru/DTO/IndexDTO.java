package kr.kamgaru.DTO;

public class IndexDTO {
	private String comcode;
	private String comtitle;
	private String comcontents;
	private String comimage;
	private String groupname;
	private String title;
	private String contents;
	private String hit;
	private String image;
	private String enddate;
	private String place;
	private String boardid;
	

	public String getComcode() {
		return comcode;
	}

	public void setComcode(String comcode) {
		this.comcode = comcode;
	}

	public String getComtitle() {
		return comtitle;
	}

	public void setComtitle(String comtitle) {
		this.comtitle = comtitle;
	}

	public String getComcontents() {
		return comcontents;
	}

	public void setComcontents(String comcontents) {
		this.comcontents = comcontents;
	}

	public String getComimage() {
		return comimage;
	}

	public void setComimage(String comimage) {
		this.comimage = comimage;
	}
	
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getBoardid() {
		return boardid;
	}

	public void setBoardid(String boardid) {
		this.boardid = boardid;
	}

	public IndexDTO(){}
	
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
