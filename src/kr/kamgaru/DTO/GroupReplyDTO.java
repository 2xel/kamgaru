package kr.kamgaru.DTO;

public class GroupReplyDTO {
	private int GroupCN;
	private int BoardID;
	private int MemCode;
	private String EnrollDate;
	private String Contents;
	
	
	public GroupReplyDTO() {
		
	}
	
	
	public GroupReplyDTO(int groupCN, int boardID, int memCode) {
		
		GroupCN = groupCN;
		BoardID = boardID;
		MemCode = memCode;
	}


	public GroupReplyDTO(int groupCN, int boardID, int memCode,
			String enrollDate, String contents) {
		
		GroupCN = groupCN;
		BoardID = boardID;
		MemCode = memCode;
		EnrollDate = enrollDate;
		Contents = contents;
	}



	public int getGroupCN() {
		return GroupCN;
	}
	public void setGroupCN(int groupCN) {
		GroupCN = groupCN;
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
