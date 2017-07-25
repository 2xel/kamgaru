package kr.kamgaru.DTO;

public class CommunityReplyDTO {
		private int CommunityCN;
	    private int ComBoardID;
	    private int MemCode;
	    private String EnrollDate;
	    private String Contents;
		 
	    
		public CommunityReplyDTO(int communityCN, int comBoardID, int memCode,
				String enrollDate, String contents) {
			
			CommunityCN = communityCN;
			ComBoardID = comBoardID;
			MemCode = memCode;
			EnrollDate = enrollDate;
			Contents = contents;
		}
		public int getCommunityCN() {
			return CommunityCN;
		}
		public void setCommunityCN(int communityCN) {
			CommunityCN = communityCN;
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