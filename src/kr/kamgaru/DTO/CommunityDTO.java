package kr.kamgaru.DTO;

public class CommunityDTO {
	private int ComCode;
	private String ComTitle;
	private String ComContents;
	private String ComImage;
	private int ComBoardID;
	private int MemCode;
	private String Title;
	private String Contents;
	private String EnrollDate;
	private int Hit;
	private int LikeCount;
	private int ReplyCount;
	
	
	
	public CommunityDTO(int comCode, int comBoardID,
			int memCode, String title, String enrollDate, int hit,
			int likeCount, int replyCount) {
		ComCode = comCode;
		ComBoardID = comBoardID;
		MemCode = memCode;
		Title = title;
		EnrollDate = enrollDate;
		Hit = hit;
		LikeCount = likeCount;
		ReplyCount = replyCount;
	}

	public CommunityDTO(int comCode, int comBoardID, int memCode, String title,
			String enrollDate, int hit, int likeCount) {
		ComCode = comCode;
		ComBoardID = comBoardID;
		MemCode = memCode;
		Title = title;
		EnrollDate = enrollDate;
		Hit = hit;
		LikeCount = likeCount;
	
	}

	public CommunityDTO(int comCode, String comTitle, String comContents,
			String comImage) {
		ComCode = comCode;
		ComTitle = comTitle;
		ComContents = comContents;
		ComImage = comImage;
	}
	

	public CommunityDTO() {
	}
	
	public int getComCode() {
		return ComCode;
	}
	public void setComCode(int comCode) {
		ComCode = comCode;
	}
	public String getComTitle() {
		return ComTitle;
	}
	public void setComTitle(String comTitle) {
		ComTitle = comTitle;
	}
	public String getComContents() {
		return ComContents;
	}
	public void setComContents(String comContents) {
		ComContents = comContents;
	}
	public String getComImage() {
		return ComImage;
	}
	public void setComImage(String comImage) {
		ComImage = comImage;
	}
	public int getComBoardID() {
		return ComBoardID;
	}
	public void setComBoardID(int comBoardID) {
		ComBoardID = comBoardID;
	}
	public int getMemCode() {
		return MemCode;
	}
	public void setMemCode(int memCode) {
		MemCode = memCode;
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
	public int getHit() {
		return Hit;
	}
	public void setHit(int hit) {
		Hit = hit;
	}
	public int getLikeCount() {
		return LikeCount;
	}
	public void setLikeCount(int likeCount) {
		LikeCount = likeCount;
	}

	public int getReplyCount() {
		return ReplyCount;
	}

	public void setReplyCount(int replyCount) {
		ReplyCount = replyCount;
	}
	
	
}
