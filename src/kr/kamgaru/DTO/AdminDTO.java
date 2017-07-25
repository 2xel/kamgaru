package kr.kamgaru.DTO;

public class AdminDTO {
	
	private int BoardId; // not null
	private String FieldCode; // not null
	private String CategoryCode; // not null

	private String Title; // not null
	private String Contents; // not null
	private String EnrollDate; // not null
	private String StartDate; // not null
	private String EndDate; // not null
	private String Website;
	private String Image;// not null / attach
	private String PrizeMoney;
	private int LikeCount;
	private int Hit;
	private int Flag;
	private String Manager_name;
	private String Manager_phone;
	private String Place;

	public AdminDTO() {
	}

	public AdminDTO(int boardId, String fieldCode, String categoryCode,
			String title, String contents, String enrollDate, String startDate,
			String endDate, String website, String attach, String prizeMoney,
			int likeCount, int hit, int flag, String manager_name,
			String manager_phone, String place) {
		super();
		BoardId = boardId;
		FieldCode = fieldCode;
		CategoryCode = categoryCode;
		Title = title;
		Contents = contents;
		EnrollDate = enrollDate;
		StartDate = startDate;
		EndDate = endDate;
		Website = website;
		Image = attach;
		PrizeMoney = prizeMoney;
		LikeCount = likeCount;
		Hit = hit;
		Flag = flag;
		Manager_name = manager_name;
		Manager_phone = manager_phone;
		Place = place;
	}

	public int getBoardId() {
		return BoardId;
	}

	public void setBoardId(int boardId) {
		BoardId = boardId;
	}

	public String getFieldCode() {
		return FieldCode;
	}

	public void setFieldCode(String fieldCode) {
		FieldCode = fieldCode;
	}

	public String getCategoryCode() {
		return CategoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		CategoryCode = categoryCode;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getContents() {
		return Contents;
	}

	public void setContents(String contents) {
		Contents = contents;
	}

	public String getEnrollDate() {
		return EnrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		EnrollDate = enrollDate;
	}

	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	public String getWebsite() {
		return Website;
	}

	public void setWebsite(String website) {
		Website = website;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getPrizeMoney() {
		return PrizeMoney;
	}

	public void setPrizeMoney(String prizeMoney) {
		PrizeMoney = prizeMoney;
	}

	public int getLikeCount() {
		return LikeCount;
	}

	public void setLikeCount(int likeCount) {
		LikeCount = likeCount;
	}

	public int getHit() {
		return Hit;
	}

	public void setHit(int hit) {
		Hit = hit;
	}

	public int getFlag() {
		return Flag;
	}

	public void setFlag(int flag) {
		Flag = flag;
	}

	public String getPlace() {
		return Place;
	}

	public void setPlace(String place) {
		Place = place;
	}

	public String getManager_name() {
		return Manager_name;
	}

	public void setManager_name(String manager_name) {
		Manager_name = manager_name;
	}

	public String getManager_phone() {
		return Manager_phone;
	}

	public void setManager_phone(String manager_phone) {
		Manager_phone = manager_phone;
	}

}