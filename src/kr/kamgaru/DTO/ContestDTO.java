package kr.kamgaru.DTO;

public class ContestDTO {

	private int BoardId;
	private String CategoryCode;
	private String FieldCode;
	private String Title;
	private String Contents;
	private String EnrollDate;
	private String StartDate;
	private String EndDate;
	private String Website;
	private String Image;
	private String PrizeMoney;
	private int LikeCount;
	private int Hit;
	private int Flag;
	private String Place;
	private String AppName;
	private String AppPhone;
	private String GroupName;
	private String fieldTitle;
	private int contestCn;
	private String replyEnrollDate;
	private String replyContents;
	private int memcode;
	
	public ContestDTO(){}

	public int getBoardId() {
		return BoardId;
	}

	public void setBoardId(int boardId) {
		BoardId = boardId;
	}

	public String getCategoryCode() {
		return CategoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		CategoryCode = categoryCode;
	}

	public String getFieldCode() {
		return FieldCode;
	}

	public void setFieldCode(String fieldCode) {
		FieldCode = fieldCode;
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

	public String getAppName() {
		return AppName;
	}

	public void setAppName(String appName) {
		AppName = appName;
	}

	public String getAppPhone() {
		return AppPhone;
	}

	public void setAppPhone(String appPhone) {
		AppPhone = appPhone;
	}

	public String getGroupName() {
		return GroupName;
	}

	public void setGroupName(String groupName) {
		GroupName = groupName;
	}

	public String getFieldTitle() {
		return fieldTitle;
	}

	public void setFieldTitle(String fieldTitle) {
		this.fieldTitle = fieldTitle;
	}

	public int getContestCn() {
		return contestCn;
	}

	public void setContestCn(int contestCn) {
		this.contestCn = contestCn;
	}

	public String getReplyEnrollDate() {
		return replyEnrollDate;
	}

	public void setReplyEnrollDate(String replyEnrollDate) {
		this.replyEnrollDate = replyEnrollDate;
	}

	public String getReplyContents() {
		return replyContents;
	}

	public void setReplyContents(String replyContents) {
		this.replyContents = replyContents;
	}

	public int getMemcode() {
		return memcode;
	}

	public void setMemcode(int memcode) {
		this.memcode = memcode;
	}
}