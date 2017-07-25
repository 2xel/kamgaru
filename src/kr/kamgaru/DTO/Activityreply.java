package kr.kamgaru.DTO;

public class Activityreply {
	private int ActivityCN; //
	private int BoardID;  // boardid 시퀀스
	private int MemCode; //membercode 시퀀스
	private String EnrollDate;
	private String Contents;
	private String ReplyEnrollDate;
	private String ReplyContents;
	private String Title;
	
	public Activityreply() {

	}

	/*//지울때??
	public Activityreply(int activityCN, int boardID, int memCode) {

		ActivityCN = activityCN;
		BoardID = boardID;
		MemCode = memCode;
	}*/

	public String getReplyEnrollDate() {
		return ReplyEnrollDate;
	}

	public void setReplyEnrollDate(String replyEnrollDate) {
		ReplyEnrollDate = replyEnrollDate;
	}

	public String getReplyContents() {
		return ReplyContents;
	}

	public void setReplyContents(String replyContents) {
		ReplyContents = replyContents;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	//댓글 쓸때
	public Activityreply(int activityCN, int boardID, int memCode,
			String enrollDate, String contents) {

		ActivityCN = activityCN;
		BoardID = boardID;
		MemCode = memCode;
		EnrollDate = enrollDate;
		Contents = contents;
	}

	public int getActivityCN() {
		return ActivityCN;
	}

	public void setActivityCN(int activityCN) {
		ActivityCN = activityCN;
	}

	public int getBoardID() {
		return BoardID;
	}

	public void setBoardID(int boardID) {
		BoardID = boardID;
	}

	public int getMemCode() {
		return MemCode;
	}

	public void setMemCode(int memCode) {
		MemCode = memCode;
	}

	public String getEnrollDate() {
		return EnrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		EnrollDate = enrollDate;
	}

	public String getContents() {
		return Contents;
	}

	public void setContents(String contents) {
		Contents = contents;
	}

}