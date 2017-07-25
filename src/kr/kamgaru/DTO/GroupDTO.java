package kr.kamgaru.DTO;

public class GroupDTO {
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
	private int ReplyCount;
	private int Totalcont;
	
	
	
	public GroupDTO() {
		
	}
	
	
	public GroupDTO(int boardId, String categoryCode, String fieldCode,
			String title, String contents, String enrollDate, String startDate,
			String endDate, String website, String image, String prizeMoney,
			int likeCount, int hit, String place ) {
		super();
		BoardId = boardId;
		CategoryCode = categoryCode;
		FieldCode = fieldCode;
		Title = title;
		Contents = contents;
		EnrollDate = enrollDate;
		StartDate = startDate;
		EndDate = endDate;
		Website = website;
		Image = image;
		PrizeMoney = prizeMoney;
		LikeCount = likeCount;
		Hit = hit;
		Place = place;
		
	}


	public GroupDTO(int boardId, String fieldCode, String title, int hit,
			String place) {
		
		BoardId = boardId;
		FieldCode = fieldCode;
		Title = title;
		Hit = hit;
		Place = place;
	}
	public GroupDTO(int boardId, String fieldCode, String title, int hit,
			String place, String startdate, int replyCount) {
		
		BoardId = boardId;
		FieldCode = fieldCode;
		Title = title;
		Hit = hit;
		Place = place;
		StartDate = startdate;
		ReplyCount = replyCount;
	
	}
	public GroupDTO(int boardId, String fieldCode, String title, int hit,
			String place, String startdate, int replyCount, String Groupname) {
		
		BoardId = boardId;
		FieldCode = fieldCode;
		Title = title;
		Hit = hit;
		Place = place;
		StartDate = startdate;
		ReplyCount = replyCount;
		GroupName= Groupname;
	}


	public GroupDTO(int boardId, String title, String contents,
			String startDate, String endDate, String image, int hit)
	{
		
		BoardId = boardId;
		Title = title;
		Contents = contents;
		StartDate = startDate;
		EndDate = endDate;
		Image = image;
		Hit = hit;
	}

	public GroupDTO(int boardId, String categoryCode, String fieldCode,
			String title, String contents, String enrollDate, String startDate,
			String endDate, String website, String image, String prizeMoney,
			int likeCount, int hit, int flag, String appName, String appPhone) {

		BoardId = boardId;
		CategoryCode = categoryCode;
		FieldCode = fieldCode;
		Title = title;
		Contents = contents;
		EnrollDate = enrollDate;
		StartDate = startDate;
		EndDate = endDate;
		Website = website;
		Image = image;
		PrizeMoney = prizeMoney;
		LikeCount = likeCount;
		Hit = hit;
		Flag = flag;
		AppName = appName;
		AppPhone = appPhone;
	}
	
	


	public String getGroupName() {
		return GroupName;
	}


	public void setGroupName(String groupName) {
		GroupName = groupName;
	}


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
	public void setPlace(String place){
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


	public String getFieldTitle() {
		return fieldTitle;
	}


	public void setFieldTitle(String fieldTitle) {
		this.fieldTitle = fieldTitle;
	}


	public int getReplyCount() {
		return ReplyCount;
	}


	public void setReplyCount(int replyCount) {
		ReplyCount = replyCount;
	}


	public int getTotalcont() {
		return Totalcont;
	}


	public void setTotalcont(int totalcont) {
		Totalcont = totalcont;
	}

	
}
