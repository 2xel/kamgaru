package kr.kamgaru.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.kamgaru.DTO.ActivityDTO;
import kr.kamgaru.DTO.Activityreply;
import kr.kamgaru.DTO.AdminDTO;
import kr.kamgaru.DTO.CommunityDTO;
import kr.kamgaru.DTO.CommunityReplyDTO;
import kr.kamgaru.DTO.ContestDTO;
import kr.kamgaru.DTO.CultureDTO;
import kr.kamgaru.DTO.GroupDTO;
import kr.kamgaru.DTO.GroupReplyDTO;
import kr.kamgaru.DTO.MemberDTO;

public class DAO {
	static DataSource datasource = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String saveDir = DAO.class.getResource("").getPath();
//	String saveDir = "C:/Kosta/WebLab/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Kamgaru_28/upload/";
	public DAO() throws NamingException, SQLException {
		Context context = new InitialContext();

		datasource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	}

	/************************** 관리자 시작 **************************/
	/* 게시요청 글쓰기 */
	public int writeRequest(AdminDTO writeReq) {
		int resultrow = 0;
		String sql = "";
		try {
			conn = datasource.getConnection();
			if (writeReq.getCategoryCode().equals("C01")) { // 동아리
				sql = "insert into grouplist(BoardId, Categorycode, FieldCode, title,"
						+ "contents, EnrollDate, StartDate, EndDate,"
						+ "Website, Image, Prizemoney, likecount, hit,"
						+ "flag, place, Appname, AppPhone) values("
						+ " boardid.nextval,?,?,?,?,to_char(sysdate, 'mm/dd hh24:mi'),?,?,?,?,?,0,0,0,?,?,?)";

			} else if (writeReq.getCategoryCode().equals("C02")) { // 대외활동
				sql = "insert into activitylist(BoardId, Categorycode, FieldCode, title,contents, EnrollDate, StartDate, EndDate, Website, Image, Prizemoney, hit,flag, place, Appname, AppPhone) values("
						+ " boardid.nextval,?,?,?,?,to_char(sysdate, 'mm/dd hh24:mi'),?,?,?,?,?,0,0,?,?,?)";

			} else if (writeReq.getCategoryCode().equals("C03")) { // 공모전
				sql = "insert into contestlist(BoardId, Categorycode, FieldCode, title,"
						+ "contents, EnrollDate, StartDate, EndDate,"
						+ "Website, Image, Prizemoney, hit,"
						+ "flag, place, Appname, AppPhone) values("
						+ " boardid.nextval,?,?,?,?,to_char(sysdate, 'mm/dd hh24:mi'),?,?,?,?,?,0,0,?,?,?)";

			} else if (writeReq.getCategoryCode().equals("C04")) { // 문화정보
				sql = "insert into culturelist(BoardId, Categorycode, FieldCode, title,"
						+ "contents, EnrollDate, StartDate, EndDate,"
						+ "Website, Image, Event, hit,"
						+ "flag, place, Appname, AppPhone) values("
						+ " boardid.nextval,?,?,?,?,to_char(sysdate, 'mm/dd hh24:mi'),?,?,?,?,?,0,0,?,?,?)";
			}

			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println("DAO > writeReq-CateCode : "
					+ writeReq.getCategoryCode());

			ps.setString(1, writeReq.getCategoryCode());
			ps.setString(2, writeReq.getFieldCode());
			ps.setString(3, writeReq.getTitle());
			ps.setString(4, writeReq.getContents());
			ps.setString(5, writeReq.getStartDate());
			ps.setString(6, writeReq.getEndDate());
			ps.setString(7, writeReq.getWebsite());
			ps.setString(8, writeReq.getImage());
			ps.setString(9, writeReq.getPrizeMoney());
			ps.setString(10, writeReq.getPlace());
			ps.setString(11, writeReq.getManager_name());
			ps.setString(12, writeReq.getManager_phone());

			resultrow = ps.executeUpdate();

			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			resultrow = 0;
		}
		System.out.println("**DAO > writeRequest-resultrow : " + resultrow);
		return resultrow;
	} // writeRequest()

	/* 관리자페이지 - 전체 게시글 목록보기 */
	public List<AdminDTO> requestList(int cpage, int pagesize) throws Exception {

		System.out.println("**DAO > requestList-OK");
		ArrayList<AdminDTO> list = null;

		try { 
			conn = datasource.getConnection();

			String sql = "select * from ("
					+ "select rownum rn, categorycode, boardid,FieldCode,title,flag, enrolldate,appname from ( "
					+ "select * from ("
					+ "(select categorycode, boardid,FieldCode,title,flag,enrolldate,appname from grouplist)"
					+ "union all (select categorycode, boardid,FieldCode,title,flag,enrolldate,appname from activitylist)"
					+ "union all (select categorycode, boardid,FieldCode,title,flag,enrolldate,appname from contestlist)"
					+ "union all (select categorycode, boardid,FieldCode,title,flag,enrolldate,appname from culturelist)"
					+ ") order by enrolldate desc"
					+ ")) where rn between ? and ?";
			// select * from ( select rownum rn, categorycode, boardid,FieldCode,title,contents,flag, enrolldate from ( select * from ( (select categorycode, boardid,FieldCode,title,contents,flag,enrolldate from grouplist) union all (select categorycode, boardid,FieldCode,title,contents,flag,enrolldate from activitylist) union all (select categorycode, boardid,FieldCode,title,contents,flag,enrolldate from contestlist) union all (select categorycode, boardid,FieldCode,title,contents,flag,enrolldate from culturelist) ) order by enrolldate desc ) ) where rn between 1 and 7
			System.out.println("SQL 확인 : " + sql);
			pstmt = conn.prepareStatement(sql);

			int start = cpage * pagesize - (pagesize - 1);
			int end = cpage * pagesize;
			
			System.out.println("**DAO > adminDTOlist-Start : " + start);
			System.out.println("**DAO > adminDTOlist-end : " + end);

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();
			System.out.println("**DAO > RequestList-rs :" + rs);
			// 객체 형태로 DB가지고 데이터
			list = new ArrayList<AdminDTO>();

			AdminDTO dto;

			// 1
			while (rs.next()) {
				System.out.println("**DAO-rs/get/Cate : "
						+ rs.getString("categorycode"));
				dto = new AdminDTO();
				dto.setCategoryCode(rs.getString("categorycode"));
				dto.setBoardId(rs.getInt("boardid"));
				dto.setFieldCode(rs.getString("FieldCode"));
				dto.setTitle(rs.getString("title"));
				dto.setManager_name(rs.getString("appname"));
				dto.setFlag(rs.getInt("flag"));
				list.add(dto);
			}

			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();// 반납

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("**DAO > requestLIST-listsize : " + list.size());
		return list;
	} // requestList(int cpage, int pagesize)

	/* 게시물 총 건수 구하기 */
	public int totalMemberCount() throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			String sql = "select count(*) cnt from memberinfo";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int totalcount = 0;
			if (rs.next()) {
				totalcount = rs.getInt("cnt");
			}
			return totalcount;
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	} // totalboardCount()
	
	/* 게시물 총 건수 구하기 */
	public int totalboardCount() throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			String sql = "select sum(groupcnt + activitycnt + contestcnt + culturecnt) cnt from (select g.groupcnt, a.activitycnt, c.contestcnt, cul.culturecnt from (select count(*) groupcnt from grouplist) g,(select count(*) activitycnt from activitylist) a, (select count(*) contestcnt from contestlist) c,(select count(*) culturecnt from culturelist) cul)";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int totalcount = 0;
			if (rs.next()) {
				totalcount = rs.getInt("cnt");
			}
			return totalcount;
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	} // totalboardCount()

	/* 관리자페이지 - 글 게시하기 */
	public AdminDTO requestOk(int boardid, String cateCode) throws SQLException {

		AdminDTO dto = new AdminDTO();
		System.out.println("getContent: " + boardid);

		try {
			conn = datasource.getConnection();
			String sql="null";
			
			if (cateCode.equals("C01")) { // 동아리
				sql = "select * from grouplist where boardid=?";
			} else if (cateCode.equals("C02")) { // 대외활동
				sql = "select * from Activitylist where boardid=?";
			} else if (cateCode.equals("C03")) { // 공모전
				sql = "select * from contestlist where boardid=?";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardid);

			rs = pstmt.executeQuery();
			rs.next();

			dto.setManager_name(rs.getString("appName"));
			dto.setManager_phone(rs.getString("appPhone"));
			dto.setFieldCode(rs.getString("FieldCode"));
			dto.setCategoryCode(rs.getString("CategoryCode"));
			dto.setTitle(rs.getString("Title"));
			dto.setContents(rs.getString("Contents"));
			dto.setStartDate(rs.getString("StartDate"));
			dto.setEndDate(rs.getString("EndDate"));
			dto.setWebsite(rs.getString("Website"));
			dto.setPrizeMoney(rs.getString("PrizeMoney"));
			dto.setPlace(rs.getString("Place"));
			dto.setBoardId(rs.getInt("Boardid"));

			System.out.println("**DTO > requestOK-TITLE : "
					+ rs.getString("Title"));

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		}
		return dto;
	} // requestOk()

	/* 관리자-동아리게시판 글 게시하기 */
	public int requestUpdateConfirm(String boardid, AdminDTO dto) throws SQLException {
	
		System.out.println("게시판 글 게시하기-BoardID"+ boardid);
		int result;

		try {
			conn = datasource.getConnection();
			String sql="null";
			
			if (dto.getCategoryCode().equals("C01")) { // 동아리
				sql = "update grouplist set FieldCode=?, title=?, contents=?,Website=?, flag=1, place=? where boardid=?";
			} else if (dto.getCategoryCode().equals("C02")) { // 대외활동
				sql = "update activitylist set FieldCode=?, title=?, contents=?,Website=?, flag=1, place=? where boardid=?";
			} else if (dto.getCategoryCode().equals("C03")) { // 공모전
				sql = "update contestlist set FieldCode=?, title=?, contents=?,Website=?, flag=1, place=? where boardid=?";
				System.out.println("공모전");
			}
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getFieldCode());
			System.out.println( "dto.getFieldCode()   "+  dto.getFieldCode());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContents());
			pstmt.setString(4, dto.getWebsite());
			pstmt.setString(5, dto.getPlace());
			pstmt.setString(6, boardid);
			System.out.println( "dto.getFieldCode()   "+  boardid);


			result = pstmt.executeUpdate();

			System.out.println("**DAO > requestConfirm-result : " + result);

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		}
		return result;
	} // requestUpdateConfirm()
	
	/* 관리자-동아리게시판 글 게시하기 */
	public int requestConfirm(String boardid, AdminDTO dto) throws SQLException {
	
		System.out.println("게시판 글 게시하기-BoardID"+ boardid);
		int result;

		try {
			conn = datasource.getConnection();
			String sql="null";
			
			if (dto.getCategoryCode().equals("C01")) { // 동아리
				sql = "update grouplist set flag=1 where boardid=?";
			} else if (dto.getCategoryCode().equals("C02")) { // 대외활동
				sql = "update activitylist set flag=1 where boardid=?";
			} else if (dto.getCategoryCode().equals("C03")) { // 공모전
				sql = "update contestlist set flag=1 where boardid=?";
				System.out.println("공모전");
			}
			
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, boardid);
			System.out.println( "dto.getFieldCode()   "+  boardid);


			result = pstmt.executeUpdate();

			System.out.println("**DAO > requestConfirm-result : " + result);

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		}
		return result;
	} // requestConfirm()

	/* 관리자-회원 목록조회 */
	public List<MemberDTO> memberList(int cpage, int pagesize) throws Exception {

		System.out.println("**DAO > MemberDTOmemberList-TEST");
		ArrayList<MemberDTO> list = null;

		try {
			conn = datasource.getConnection();

			String sql = "select * from (select ROWNUM rn ,memcode,nickname,pwd,name,email,university from (select * from memberinfo order by memcode desc)) where rn Between ? and ?";
			pstmt = conn.prepareStatement(sql);

			int start = cpage * pagesize - (pagesize - 1);
			int end = cpage * pagesize;
			System.out.println("MemberDTO-Start : " + start);
			System.out.println("MemberDTO-end : " + end);

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			// 객체 형태로 DB가지고 데이터
			list = new ArrayList<MemberDTO>();
			System.out.println("**DAO > MemberDTO-list OK");

			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setName(rs.getString("memcode"));
				dto.setNickname(rs.getString("nickname"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setUniversity(rs.getString("university"));
				list.add(dto);
			}

			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();// 반납

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	} // memberList(int cpage, int pagesize)

	/* 관리자-회원삭제 */
	public int memberDelete(String nickname) throws SQLException {
		conn = datasource.getConnection();
		int result = 0;
		try {

			System.out.println("**DAO > Delete : " + nickname);
			String sql = "delete from memberinfo where nickname=?";
			System.out.println("**DAO > Delete OK");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickname);
			result = pstmt.executeUpdate();
			System.out.println("**DAO > DeleteOK result: " + result);
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		}
		return result;
	} // memberDelete()

	/* 관리자-게시글삭제 */
	public int boardDelete(int boardid, String catecode) throws SQLException {
		conn = datasource.getConnection();
		int result = 0;
		String sql = "";
		try {
			if (catecode.equals("C01")) {
				sql = "delete from grouplist where boardid=?";
			} else if (catecode.equals("C02")) {
				sql = "delete from activitylist where boardid=?";
			} else if (catecode.equals("C03")) {
				sql = "delete from contestlist where boardid=?";
			} else if (catecode.equals("C04")) {
				sql = "delete from culturelist where boardid=?";
			}
			System.out.println("**DAO > Delete : " + boardid);
			System.out.println("**DAO > DeleteSQL : " + sql);

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardid);
			result = pstmt.executeUpdate();
			System.out.println("**DAO > DeleteOK result: " + result);
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		}
		return result;
	} // boardDelete()

	/********************************** 관리자 끝 **************************/

	/********************************* 공모전 시작 ************************/

	/* 공모전 게시글목록조회 */
	/* 게시물 총 건수 구하기 */
	public int ContestTotalboardCount() throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			String sql = "select count(*) cnt from contestlist";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int totalcount = 0;
			if (rs.next()) {
				totalcount = rs.getInt("cnt");
			}
			return totalcount;
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	} // totalboardCount()
	
	/*공모전 팀원모집 총 게시글(댓글) 수*/
	public int TeamTotalboardCount() throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			String sql = "select count(*) cnt from contestcomment";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int totalcount = 0;
			if (rs.next()) {
				totalcount = rs.getInt("cnt");
			}
			return totalcount;
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	} // TeamTotalboardCount()
	
	/* 공모전 -게시글목록 보여주기*/
	public List<ContestDTO> contestList(int cpage, int pagesize,String select_category, String select_order)
			throws Exception {
		System.out.println("***DAO > contestList-TEST");
		ArrayList<ContestDTO> list = null;

		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String sDate = sdf.format(d);
		Date startDate = sdf.parse(sDate);
		String dDay = null;

		String where_sql = "";
		String order_sql = "";
		//이런식으로
		if(select_category != null){
			//빈값이 아닐때 조건 주기
			if(!"".equals(select_category)){
				//ㅋㅋ테이블이랑 where이랑 붙어버림 띄어쓰기 안해줘서 
				where_sql = " where fieldcode = '"+select_category+"'";
			}
		}
		
		if(select_order != null){
			if("new".equals(select_order)){
				//최신순
				order_sql = " order by BoardId desc";
			}else if("end".equals(select_order)){
				//마감순
				order_sql = " order by enddate desc";
			}else{
				//최신순
				order_sql = " order by BoardId desc";
			}
		}else{
			//최신순
			order_sql = " order by BoardId desc";
		}

		try {
			conn = datasource.getConnection();

			//String sql =  "select BoardId,title,FieldCode,Place,hit,enddate,(select count(*) from groupcomment b where BoardId = a.BoardId) replyCount from GroupList a"+where_sql+order_sql;

			String sql = "select * from (select ROWNUM rn ,boardid,FieldCode,title,contents,place,image,EndDate,StartDate from (select * from contestlist "+where_sql+order_sql+")where flag=1) where rn Between ? and ?";
			pstmt = conn.prepareStatement(sql);

			int start = cpage * pagesize - (pagesize - 1);
			int end = cpage * pagesize;

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();
			System.out.println("DAO > contestList-rs: " + rs);
			list = new ArrayList<ContestDTO>();

			while (rs.next()) {
				ContestDTO dto = new ContestDTO();
				dto.setBoardId(rs.getInt("boardid"));
				dto.setFieldCode(rs.getString("FieldCode"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setPlace(rs.getString("place"));
				dto.setImage(saveDir + rs.getString("Image"));
				String endDate = rs.getString("EndDate");
				Date endDate2 = sdf.parse(endDate);

				long diff = endDate2.getTime() - startDate.getTime();
				long diffDays = diff / (24 * 60 * 60 * 1000);
				if (diffDays < 0) {
					dto.setStartDate("마감");
				} else {
					dDay = Long.toString(diffDays);
					dto.setStartDate("D-" + dDay);
				}
				list.add(dto);
			}
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();// 반납

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("requestLIST DAO: " + list.size());
		return list;

	} // contestList(int cpage, int pagesize)

	/* 공모전 게시판 상세보기 */
	public ContestDTO ContestBoardView(int BoardId) throws Exception {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf_kor = new SimpleDateFormat("yyyy년 MM월 dd일");

		String sDate = sdf.format(d);
		Date startDate = sdf.parse(sDate);
		String dDay = null;

		try {
			conn = datasource.getConnection();
			String sql = "select * from Contestlist where BoardID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BoardId);

			rs = pstmt.executeQuery();
			ContestDTO dto = new ContestDTO();

			if (rs.next()) {
				dto.setBoardId(rs.getInt("boardid"));
				dto.setCategoryCode(rs.getString("CategoryCode"));
				dto.setFieldCode(rs.getString("FieldCode"));
				dto.setTitle(rs.getString("Title"));
				dto.setContents(rs.getString("Contents"));
				// String tempDate = sdf_kor.format(rs.getString("StartDate"));

				dto.setStartDate(rs.getString("StartDate"));
				dto.setEndDate(rs.getString("EndDate"));
				dto.setWebsite(rs.getString("Website"));
				dto.setImage(saveDir + rs.getString("Image"));
				dto.setPrizeMoney(rs.getString("PrizeMoney"));
				dto.setHit(rs.getInt("Hit"));
				dto.setPlace(rs.getString("Place"));

				String endDate = rs.getString("EndDate");

				Date endDate2 = sdf.parse(endDate);
				long diff = endDate2.getTime() - startDate.getTime();
				long diffDays = diff / (24 * 60 * 60 * 1000);

				if (diffDays < 0) {
					dto.setEnrollDate("마감");
				} else {
					dDay = Long.toString(diffDays);
					System.out.println("ddd" + dDay);
					dto.setEnrollDate("D-" + dDay);
				}
			}

			return dto;

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		}
	}// ContestBoardView(int BoardId)

	/* 공모전 댓글보기 */
	public ArrayList<ContestDTO> contestComment(int boardid, int cpage,
			int pagesize) throws Exception {
		System.out.println("***DAO > contestComment-TEST");
		ArrayList<ContestDTO> list = new ArrayList<ContestDTO>();

		try {
			conn = datasource.getConnection();

			String sql = "select * from (select ROWNUM rn ,contestCN,boardid,memcode,enrolldate,contents from (select * from contestcomment where boardid= ? order by enrolldate desc))";
			pstmt = conn.prepareStatement(sql);

			int start = cpage * pagesize - (pagesize - 1);
			int end = cpage * pagesize;

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardid);
			rs = pstmt.executeQuery();

			System.out.println("boardid : " + boardid + " start : " + start
					+ " end : " + end);

			while (rs.next()) {
				ContestDTO dto = new ContestDTO();
				dto.setBoardId(rs.getInt("boardid"));
				dto.setContestCn(rs.getInt("contestCN"));
				dto.setMemcode(rs.getInt("memcode"));
				dto.setReplyEnrollDate(rs.getString("EnrollDate"));
				dto.setReplyContents(rs.getString("contents"));
				System.out.println("COMMENT : " + rs.getInt("boardid"));
				list.add(dto);
			}

			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();// 반납

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	} // contestList(int cpage, int pagesize)

	/* 공모전 팀원모집 보기 */
	public ArrayList<ContestDTO> contestTeam(int cpage, int pagesize)
			throws Exception {
		System.out.println("***DAO > contestTeam-TEST");
		ArrayList<ContestDTO> list = new ArrayList<ContestDTO>();

		try {
			conn = datasource.getConnection();

			// String sql =
			// "select * from (select ROWNUM rn ,contestCN,boardid,memcode,enrolldate,contents from (select * from contestcomment where boardid= ? order by contestCN desc)) where rn Between ? and ?";
			String sql = "select * from (select ROWNUM rn, m.enrolldate,m.boardid,m.contestCN, m.memcode, m.contents,l.title  from contestcomment m join contestList l on m.boardid = l.boardid order by contestCN desc) where rn Between ? and ?";

			pstmt = conn.prepareStatement(sql);

			int start = cpage * pagesize - (pagesize - 1);
			int end = cpage * pagesize;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();

			
			while (rs.next()) {
				ContestDTO dto = new ContestDTO();
				dto.setBoardId(rs.getInt("boardid"));
				dto.setContestCn(rs.getInt("contestCN"));
				dto.setMemcode(rs.getInt("memcode"));
				dto.setReplyEnrollDate(rs.getString("EnrollDate"));
				dto.setReplyContents(rs.getString("contents"));
				dto.setTitle(rs.getString("title"));

				System.out.println("COMMENT : " + rs.getInt("boardid"));
				list.add(dto);
			}

			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();// 반납

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	} // contestTeam(int cpage, int pagesize)
	
	/* 공모전 덧글쓰기 */
	public int contestReplyWrite(String boardid,int memcode,String contents) {
		int resultrow = 0;
		String sql = "";
		try {
			conn = datasource.getConnection();
			sql="insert into contestcomment values(contestcomsq.nextval,?,?,to_char(sysdate, 'mm/dd hh24:mi'),?)";

			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println("ContestDAO: "+ boardid);
			
			ps.setString(1, boardid);
			ps.setInt(2, memcode);
			ps.setString(3, contents);
		

			resultrow = ps.executeUpdate();

			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("**DAO > writeRequest-resultrow : " + resultrow);
		
		return resultrow;
	} // contestReplyWrite(String boardid,int memcode,String contents)
	
	/*공모전 댓글 삭제 */
	public int ContestCommentDelete(String BoardID, String ContestCn) throws SQLException {
		int rs;
		try {
			conn = datasource.getConnection();
			System.out.println("DAO COntestCn: "+ContestCn +" " +BoardID);
			String sql = "delete from contestcomment where BoardID = ? and ContestCn = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, BoardID);
			pstmt.setString(2, ContestCn);

			rs = pstmt.executeUpdate();
			

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close(); // pool conn 객체반환
		}
		return rs;
	}
	
	public boolean contestGetHit(int BoardId) throws SQLException {
		try {
			conn = datasource.getConnection();
			String sql = "update contestlist set hit=hit+1 where BoardID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BoardId);
			int row = pstmt.executeUpdate();

			if (row > 0) {
				return true;
			} else {
				return false;
			}

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		}
	}//contestGetHit(int BoardId)
	
	//공모전 페이징 처리를 위해서 
	public int ContestTotalboardCount(String select_category) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "select count(*) cnt from contestlist where flag=1";
			if(select_category != null){
				if(!"".equals(select_category)){
					sql +=" and fieldcode = ?";
				}
			}
			
			pstmt = conn.prepareStatement(sql);
			
			if(select_category != null){
				if(!"".equals(select_category)){
					pstmt.setString(1, select_category);
				}
			}
			
			rs = pstmt.executeQuery();
			int totalcount = 0;
			if (rs.next()) {
				totalcount = rs.getInt("cnt");
			}
			return totalcount;
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}//ContestTotalboardCount()
	/******************************** 공모전 끝 ********************************/

	/******************************** 커뮤니티 시작 **************************/

	/* 커뮤니티 글쓰기 함수 */
	public int comWrite(CommunityDTO dto) throws Exception {
		try {
			conn = datasource.getConnection();
			String sql = "insert into CommunityBoard(ComCode,ComTitle,ComContents,ComImage) values(comcode.nextval,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			// parameter 설정하기
			pstmt.setString(1, dto.getComTitle());
			pstmt.setString(2, dto.getComContents());
			pstmt.setString(3, dto.getComImage()); 

			int row = pstmt.executeUpdate();
			return row;
		
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

	} // comWrite()

	/* 커뮤니티 글목록 보여주는 함수 */
	public List<CommunityDTO> comGetList(int cpage, int pagesize)
			throws Exception {
		List<CommunityDTO> list = null;
		try {
			conn = datasource.getConnection();
			String sql = "select * from CommunityBoard order by ComCode asc";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			// 객체 형태로 DB가지고 데이터
			list = new ArrayList<CommunityDTO>();
			while (rs.next()) {
				int comCode = rs.getInt("ComCode");
				String comTitle = rs.getString("ComTitle");
				String comContents = rs.getString("ComContents");
				String comImage = rs.getString("ComImage");

				System.out.println(comTitle + "comTitlecomTitle");
				CommunityDTO dto = new CommunityDTO(comCode, comTitle, comContents, comImage);
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			conn.close();
			return list;

		}
	} // comGetList()

	/* 커뮤니티 sub글쓰기 함수 */
	public int comSubWrite(String ComCode, String MemCode, String Title) throws Exception {
		System.out.println("뜰어갓숨니까?");
		try {
			conn = datasource.getConnection();
			String sql = "insert into communityList(ComBoardID, ComCode, MemCode, Title, EnrollDate, Hit, LikeCount) values(comboardid.nextval,5,1,?,to_char(sysdate, 'mm/dd hh24:mi'),0,0)";
			pstmt = conn.prepareStatement(sql);
			
			// parameter 설정하기
			/*pstmt.setString(1, ComCode);*/
			/*pstmt.setString(2, MemCode);*/
			pstmt.setString(1, Title);


			int row = pstmt.executeUpdate();
			return row;

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

	} // comSubWrite()

	/* 커뮤니티 sub목록 보여주는 함수 */
	public List<CommunityDTO> comGetView(CommunityDTO listRequest) throws Exception {
		List<CommunityDTO> list = null;
		try {
			conn = datasource.getConnection();

			String sql2 = "select a.comcode as comcode, a.comboardid as comboardid, a.memcode as memcode, a.title as title, a.enrolldate as enrolldate, a.hit as hit, (select count(*) from communitycomment where ComBoardID= a.ComBoardID) as reply_count from communitylist a";
			/*String sql = "select a.comcode as comcode, a.comboardid as comboardid, a.memcode as memcode, "
			      + "a.title as title, a.enrolldate as enrolldate, a.hit as hit, "
			      + "("
			      + "select count(*) from communitycomment where ComBoardID = a.ComBoardID "
			      + ") as reply_count" 
				  + " from communitylist a" 
				  + " where comcode=?";*/
			pstmt = conn.prepareStatement(sql2);

			//String comCode = Integer.parseInt(listRequest.getComCode());
			/*pstmt.setInt(1, listRequest.getComCode());*/
			rs = pstmt.executeQuery();

			// 객체 형태로 DB가지고 데이터
			list = new ArrayList<CommunityDTO>();
			CommunityDTO dto;
			while (rs.next()) {
				dto = new CommunityDTO();
				dto.setComBoardID(rs.getInt("ComBoardID"));
				dto.setComCode(rs.getInt("ComCode"));
				dto.setMemCode(rs.getInt("MemCode"));
				dto.setTitle(rs.getString("Title"));
				dto.setEnrollDate(rs.getString("EnrollDate"));
				dto.setHit(rs.getInt("Hit"));
				dto.setReplyCount(rs.getInt("reply_count"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			conn.close();
		}
		return list;
	} // comGetView()

	/* 커뮤티니 게시물 총 건수 구하는 함수 */
	public int comTotalboardCount() throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			String sql = "select count(*) cnt from communityBoard";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int totalcount = 0;
			if (rs.next()) {
				totalcount = rs.getInt("cnt");
			}
			return totalcount;
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

	} // totalboardCount()

	/* 커뮤니티 게시물 조회수 증가하는 함수 */
	public boolean comGetHit(String ComBoardID) throws SQLException {
		try {
			conn = datasource.getConnection();
			String sql = "update CommunityList set hit=hit+1 where ComBoardID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ComBoardID);
			int row = pstmt.executeUpdate();

			if (row > 0) {
				return true;
			} else {
				return false;
			}

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		}
	} // comGetHit()

	/* 커뮤니티 댓글 추가하는 함수 */
	public int CommunityComment(String ComBoardID, String MemCode, String Contents)
			throws SQLException {
		try {
			conn = datasource.getConnection();       
			String sql = "insert into CommunityComment(CommunityCN, ComBoardID, MemCode, EnrollDate ,Contents) values (comcomesq.nextval , ? ,? ,to_char(sysdate, 'mm/dd hh24:mi'), ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ComBoardID);
			pstmt.setString(2, MemCode);
			pstmt.setString(3, Contents);

			int row = pstmt.executeUpdate();
			return row;

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close(); // pool conn 객체반환
		}

	}

	/* 커뮤니티 댓글 삭제하는 함수 */
	public int CommunityCommentDelete(int ComBoardID, int CommunityCn) throws SQLException {

		try {
			conn = datasource.getConnection();
			String sql = "delete from communityComment where ComBoardID=? and CommunityCn=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ComBoardID);
			pstmt.setInt(2, CommunityCn);

			int row = pstmt.executeUpdate();
			return row;

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close(); // pool conn 객체반환
		}

	}

	/* 커뮤니티 댓글 보여주는 함수 */

	public List<CommunityReplyDTO> comCommentlist(String ComBoardId) throws Exception {

		List<CommunityReplyDTO> communityCommentlist = null;
		try {
			conn = datasource.getConnection();
			String sql = "select ComBoardId,communityCN,MemCode,Enrolldate,contents from CommunityComment where ComBoardId=? order by enrolldate asc";

			/*
			 * int start = cpage * pagesize - (pagesize - 1); int end = cpage *
			 * pagesize;
			 */
			System.out.println("나와라");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ComBoardId);

			rs = pstmt.executeQuery();

			// 객체 형태로 DB가지고 데이터
			communityCommentlist = new ArrayList<CommunityReplyDTO>();
			while (rs.next()) {
				int comBoardID = rs.getInt("ComBoardId");
				int communityCN = rs.getInt("CommunityCN");
				int memCode = rs.getInt("MemCode");
				String enrollDate = rs.getString("EnrollDate");
				String contents = rs.getString("Contents");
				CommunityReplyDTO ReplyDTO = new CommunityReplyDTO(communityCN,comBoardID, memCode, enrollDate, contents);
				ReplyDTO.setCommunityCN(communityCN);
				ReplyDTO.setComBoardID(comBoardID);
				ReplyDTO.setMemCode(memCode);
				ReplyDTO.setEnrollDate(enrollDate);
				ReplyDTO.setContents(contents);
				communityCommentlist.add(ReplyDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			conn.close();
			return communityCommentlist;
		}
	}

	/* 커뮤니티 댓글 갯수 구하는 함수 */
	public int comcommentCount() throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			String sql = "select (select count(*) from communitycomment where ComBoardID= a.ComBoardID) as reply_count from communitylist a";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			int totalcount = 0;
			if (rs.next()) {
				totalcount = rs.getInt("cnt");
			}
			return totalcount;
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

	}// comcommentCount()

	/* 커뮤니티 검색하는 함수 */
	public List<CommunityDTO> comSearch(String title) throws Exception {
		List<CommunityDTO> list = null;
		try {
			conn = datasource.getConnection();
			String sql = "select * from CommunityList where title like ?";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			// 객체 형태로 DB가지고 데이터
			list = new ArrayList<CommunityDTO>();
			while (rs.next()) {
				int comCode = rs.getInt("ComCode");
				String comTitle = rs.getString("ComTitle");
				String comContents = rs.getString("ComContents");
				String comImage = rs.getString("ComImage");

				System.out.println(comTitle + "comTitlecomTitle");
				CommunityDTO dto = new CommunityDTO(comCode, comTitle,
						comContents, comImage);
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			conn.close();
			return list;

		}
	} // comSearch()
	
	/* 커뮤니티 글 삭제하는 함수 */

	public int comdelete(int comBoardId) throws SQLException {

		try {
			conn = datasource.getConnection();
			// 게시글 삭제
			String sql = "delete from communitylist where comBoardId=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comBoardId);
			int row = pstmt.executeUpdate();

			if (row > 0) {
				conn.commit(); 
			} else {
				conn.rollback();
			}

			return row;

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	} //comdelete()

	/******************************** 커뮤니티 끝 **************************/

	/******************************** 대외활동 시작 ***************************/
	//대외활동 1건뿌림
	public ActivityDTO ActivityBoardView(String BoardId) throws Exception {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf_kor = new SimpleDateFormat("yyyy년 MM월 dd일");

		String sDate = sdf.format(d);
		Date startDate = sdf.parse(sDate);
		String dDay = null;

		try {
			conn = datasource.getConnection();
			String sql = "select * from activitylist where BoardID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, BoardId);

			rs = pstmt.executeQuery();
			ActivityDTO dto = new ActivityDTO();

			if (rs.next()) {
				dto.setBoardId(rs.getInt("boardid"));
				dto.setCategoryCode(rs.getString("CategoryCode"));
				dto.setFieldCode(rs.getString("FieldCode"));
				dto.setTitle(rs.getString("Title"));
				dto.setContents(rs.getString("Contents"));
				// String tempDate = sdf_kor.format(rs.getString("StartDate"));

				dto.setStartDate(rs.getString("StartDate"));
				dto.setEndDate(rs.getString("EndDate"));
				dto.setWebsite(rs.getString("Website"));
				dto.setImage(rs.getString("Image"));
				dto.setPrizeMoney(rs.getString("PrizeMoney"));
				dto.setHit(rs.getInt("Hit"));
				dto.setPlace(rs.getString("Place"));

				String endDate = rs.getString("EndDate");

				Date endDate2 = sdf.parse(endDate);
				long diff = endDate2.getTime() - startDate.getTime();
				long diffDays = diff / (24 * 60 * 60 * 1000);

				if (diffDays < 0) {
					dto.setEnrollDate("마감");
				} else {
					dDay = Long.toString(diffDays);
					System.out.println("ddd" + dDay);
					dto.setEnrollDate("D-" + dDay);
				}
			}

			return dto;

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		}
	}// ContestBoardView(int BoardId)

	/* 대외활동 게시판 목록 여러껀뿌려주기 */
	public List<ActivityDTO> Activitylist(int cpage, int pagesize, String select_category, String select_order) throws Exception {

		List<ActivityDTO> Activitylist = null;
		String StartDate = null;

		Date d = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String sDate = sdf.format(d);
		Date startDate = sdf.parse(sDate);
		String dDay = null;
		String where_sql = "";
		String order_sql = "";
		
		//이런식으로
		if(select_category != null){
			//빈값이 아닐때 조건 주기
			if(!"".equals(select_category)){
				//테이블이랑 where이랑 붙어버림 띄어쓰기 안해줘서 
				where_sql = " and fieldcode = '"+select_category+"'";
			}
		}
		if(select_order != null){//select box 아이디
			if("new".equals(select_order)){ //select box 최신순 아이디
				//최신순
				order_sql = " order by BoardId desc";
			}else if("end".equals(select_order)){
				//마감순
				order_sql = " order by enddate desc";
			}else{
				//최신순
				order_sql = " order by BoardId desc"; //그냥 최신순
			}
		}else{
			//최신순
			order_sql = " order by BoardId desc";
		}
		
		
		try {
			conn = datasource.getConnection();
			String sql ="select * from ("
					   +"select rownum rn, BoardId,title,FieldCode,Place,hit,enddate "
			           +",(select count(5) from activitycomment b where BoardId = a.BoardId) replyCount "
					   +" from activityList a where flag=1 "+where_sql+order_sql
					   +") where rn between ? and ?";
			
			//System.out.println("sql::"+sql);
			int start = cpage * pagesize - (pagesize - 1);
			int end = cpage * pagesize;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			// 객체 형태로 DB가지고 데이터
			Activitylist = new ArrayList<ActivityDTO>();
			while (rs.next()) {
				int boardId = rs.getInt("boardid");
				String Title = rs.getString("Title");
				String FieldCode = rs.getString("FieldCode");
				String Place = rs.getString("Place");
				int hit = rs.getInt("hit");
				String endDate = rs.getString("EndDate");
				Date endDate2 = sdf.parse(endDate);

				long diff = endDate2.getTime() - startDate.getTime();
				long diffDays = diff / (24 * 60 * 60 * 1000);
				if (diffDays < 0) {
					StartDate = "마감!";
				} else {
					dDay = Long.toString(diffDays);
					StartDate = "D-" + dDay;
				}

				ActivityDTO ActivityListDTO = new ActivityDTO(boardId, FieldCode, Title,
						hit, Place, StartDate);
				Activitylist.add(ActivityListDTO); 
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			conn.close();
			return Activitylist;
		}
	}
	
	/* 대외활동 게시판 - 상세보기페이지에 댓글 뿌려주기 */
	public List<Activityreply> ActivityCommentlist(String BoardId, int cpage, int pagesize)
			throws Exception {

		List<Activityreply> activityCommentlist = null;
		try {
			conn = datasource.getConnection();
			String sql = "select BoardId,activityCN,MemCode,Enrolldate,contents from activityComment where BoardId=?";

			  int start = cpage * pagesize - (pagesize - 1);
			  int end = cpage * pagesize;
			 
			System.out.println("나와라");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, BoardId);

			rs = pstmt.executeQuery();

			// 객체 형태로 DB가지고 데이터
			activityCommentlist = new ArrayList<Activityreply>();
			while (rs.next()) {
				int boardId = rs.getInt("boardid");
				int activityCN = rs.getInt("activityCN");
				int memCode = rs.getInt("memcode");
				String enrollDate = rs.getString("Enrolldate");
				String contents = rs.getString("contents");
				Activityreply activityReplyDTO = new Activityreply();
				activityReplyDTO.setBoardID(boardId);
				activityReplyDTO.setActivityCN(activityCN);
				activityReplyDTO.setMemCode(memCode);
				activityReplyDTO.setEnrollDate(enrollDate);
				activityReplyDTO.setContents(contents);
				activityCommentlist.add(activityReplyDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			conn.close();
			return activityCommentlist;
		}
	}

	// 대외활동 페이징 처리
	public int ActivityTotalboardCount(String select_category) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			String sql = "select count(*) cnt from activitylist where flag=1";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int totalcount = 0;
			if (rs.next()) {
				totalcount = rs.getInt("cnt");
			}
			return totalcount;
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

	}

	// 대외활동 상세보기조회수 증가하는 함수 
	public boolean ActivityGetHit(String BoardId) throws SQLException {
		System.out.println("조회수 증가");
		try {
			conn = datasource.getConnection();
			String sql = "update activityList set hit=hit+1 where BoardID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, BoardId);
			int row = pstmt.executeUpdate();

			if (row > 0) {
				return true;
			} else {
				return false;
			}

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		}
	} // comGetHit() 

	
	
	/* 대외활동 팀원모집 보기 */
	public ArrayList<Activityreply> ActivityTeam(int cpage, int pagesize)
			throws Exception {
		ArrayList<Activityreply> list = new ArrayList<Activityreply>();

		try {
			conn = datasource.getConnection();
			
			String sql = "select * from ( "
							+ "select ROWNUM rn, enrolldate, boardid, activityCN, memcode, contents, title from "
							+ " (select  m.enrolldate as enrolldate, m.boardid as boardid, m.activityCN as activityCN, m.memcode as memcode, m.contents as contents, l.title as title "
							+ " from activitycomment m join activityList l on m.boardid = l.boardid "
							+ " order by activityCN desc)) where rn Between ? and ?";
			pstmt = conn.prepareStatement(sql);

			int start = cpage * pagesize - (pagesize - 1);
			int end = cpage * pagesize;

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			// System.out.println("boardid : "+boardid+" start : "+start+" end : "+end);

			while (rs.next()) {
				Activityreply dto = new Activityreply();
				dto.setBoardID(rs.getInt("boardid"));
				dto.setActivityCN(rs.getInt("activityCN"));
				dto.setMemCode(rs.getInt("memcode"));
				dto.setReplyEnrollDate(rs.getString("EnrollDate"));
				dto.setReplyContents(rs.getString("contents"));
				dto.setTitle(rs.getString("title"));

				list.add(dto);
			}

			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();// 반납

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	} 

	//대외활동 댓글 등록
		public int ActivityComment(String BoardID, String MemCode, String Contents) throws SQLException {
			System.out.println("팀에서 넘어노는");
			try {
				conn = datasource.getConnection();
				String sql = "insert into activityComment(activityCN, BoardID, MemCode, EnrollDate ,Contents) "
						+ "values ( acticomsq.nextval,? ,? ,to_char(sysdate,'mm/dd hh24:mi'), ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, BoardID);
				pstmt.setString(2, MemCode);
				pstmt.setString(3, Contents);

				int row = pstmt.executeUpdate();
				return row;

			} finally {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close(); 
			}

		}
	
		
		//대외활동 댓글 삭제하기
		public int ActivityCommentDelete(String BoardID, String ActivityCn) throws SQLException {

			try {
				conn = datasource.getConnection();
				String sql = "delete from Activitycomment where BoardID = ? and ActivityCn = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, BoardID);
				pstmt.setString(2, ActivityCn);

				int row = pstmt.executeUpdate();
				return row;

			} finally {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close(); 
			}

		}

		
	/******************************** 대외활동 끝 *****************************/


	/*********************************** 동아리 시작 *****************************/
	/* 동아리 한 건의 상세보기를 보여주는 함수 */
	public GroupDTO GroupBoardView(int BoardId) throws SQLException {
		try {
			conn = datasource.getConnection();
			String sql = "select * from GroupList a where BoardID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BoardId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String CategoryCode = rs.getString("CategoryCode");
				String FieldCode = rs.getString("FieldCode");
				String Title = rs.getString("Title");
				String Contents = rs.getString("Contents");
				String EnrollDate = rs.getString("EnrollDate");
				String StartDate = rs.getString("StartDate");
				String EndDate = rs.getString("EndDate");
				String Website = rs.getString("Website");
				String Image = rs.getString("Image");
				String PrizeMoney = rs.getString("PrizeMoney");
				int LikeCount = rs.getInt("LikeCount");
				int Hit = rs.getInt("Hit");
				String Place = rs.getString("Place");

				System.out.println("CategoryCode::" + CategoryCode);
				GroupDTO GroupBoardView = new GroupDTO(BoardId, CategoryCode,
						FieldCode, Title, Contents, EnrollDate, StartDate,
						EndDate, Website, Image, PrizeMoney, LikeCount, Hit,
						Place);
				return GroupBoardView;
			}
			return null;
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		}
	}

	/* 동아리 게시판 목록 뿌려주기 */
	public List<GroupDTO> Grouplist(int cpage, int pagesize, String select_category, String select_order) throws Exception {

		List<GroupDTO> Grouplist = null;
		String StartDate = null;

		Date d = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String sDate = sdf.format(d);
		Date startDate = sdf.parse(sDate);
		String dDay = null;
		String where_sql = "";
		String order_sql = "";
		
		//이런식으로
		if(select_category != null){
			//빈값이 아닐때 조건 주기
			if(!"".equals(select_category)){
				where_sql = " and fieldcode = '"+select_category+"'";
			}
		}
		if(select_order != null){
			if("new".equals(select_order)){
				//최신순
				order_sql = " order by BoardId desc";
			}else if("end".equals(select_order)){
				//마감순
				order_sql = " order by enddate desc";
			}else{
				//최신순
				order_sql = " order by BoardId desc";
			}
		}else{
			//최신순
			order_sql = " order by BoardId desc";
		}
		
		
		try {
			conn = datasource.getConnection();
			String sql ="select * from (select rownum rn, aa.* from ("
					   +"select BoardId,title,FieldCode,Place,hit,enddate,groupname "
			           +",(select count(*) from groupcomment b where BoardId = a.BoardId) replyCount "
					   +" from GroupList a where 1=1 and flag=1"+where_sql+order_sql+") aa) aaa "
					   +" where 1=1 and rn between ? and ?";
			
			System.out.println("sql::"+sql);
			int start = cpage * pagesize - (pagesize - 1);
			int end = cpage * pagesize;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			// 객체 형태로 DB가지고 데이터
			Grouplist = new ArrayList<GroupDTO>();
			while (rs.next()) {
				int boardId = rs.getInt("BoardId");
				String Title = rs.getString("Title");
				String FieldCode = rs.getString("FieldCode");
				String Place = rs.getString("Place");
				int hit = rs.getInt("hit");
				int replyCount = rs.getInt("replyCount");

				String endDate = rs.getString("EndDate");
				Date endDate2 = sdf.parse(endDate);
				String groupName=rs.getString("groupname");

				long diff = endDate2.getTime() - startDate.getTime();
				long diffDays = diff / (24 * 60 * 60 * 1000);
				if (diffDays < 0) {
					StartDate = "마감!";
				} else {
					dDay = Long.toString(diffDays);
					StartDate = "D-" + dDay;
				}

				GroupDTO GroupListDTO = new GroupDTO(boardId, FieldCode, Title,
						hit, Place, StartDate,replyCount,groupName);
				Grouplist.add(GroupListDTO); 
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			conn.close();
			return Grouplist;
		}
	}

	/* 동아리 게시판 댓글 뿌려주기 */
	public List<GroupReplyDTO> GroupCommentlist(int BoardId) throws Exception {

		List<GroupReplyDTO> groupCommentlist = null;
		try {
			conn = datasource.getConnection();
			String sql = "select BoardId,GroupCN,MemCode,Enrolldate,contents from GroupComment where BoardId=? order by GroupCN desc";

			/*
			 * int start = cpage * pagesize - (pagesize - 1); int end = cpage *
			 * pagesize;
			 */
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BoardId);

			rs = pstmt.executeQuery();

			// 객체 형태로 DB가지고 데이터
			groupCommentlist = new ArrayList<GroupReplyDTO>();
			while (rs.next()) {
				int boardId = rs.getInt("BoardId");
				int groupCN = rs.getInt("GroupCN");
				int memCode = rs.getInt("MemCode");
				String enrollDate = rs.getString("Enrolldate");
				String contents = rs.getString("contents");
				GroupReplyDTO groupReplyDTO = new GroupReplyDTO();
				groupReplyDTO.setBoardID(boardId);
				groupReplyDTO.setGroupCN(groupCN);
				groupReplyDTO.setMemCode(memCode);
				groupReplyDTO.setEnrollDate(enrollDate);
				groupReplyDTO.setContents(contents);
				groupCommentlist.add(groupReplyDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			conn.close();
			return groupCommentlist;
		}
	}
	//동아리 댓글 등록
		public int GroupComment(int BoardID, int MemCode, String Contents) throws SQLException {

			try {
				conn = datasource.getConnection();
				String sql = "insert into GroupComment(GroupCN, BoardID, MemCode, EnrollDate ,Contents) values ( reply_no.nextval,? ,? ,to_char(sysdate,'yyyy-mm-dd hh:mm'), ?)";
				pstmt = conn.prepareStatement(sql);
				//pstmt.setInt(1, GroupCN); //eply_no.nextval 이게 존재함
				pstmt.setInt(1, BoardID);
				pstmt.setInt(2, MemCode);
				//pstmt.setString(4, EnrollDate); //to_char(sysdate,'yyyy-mm-dd')
				pstmt.setString(3, Contents);

				int row = pstmt.executeUpdate();
				return row;

			} finally {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close(); // pool conn 객체반환
			}

		}
	
	//동아리 댓글 삭제하기
	public int GroupCommentDelete(int BoardID, int GroupCn) throws SQLException {

		try {
			conn = datasource.getConnection();
			String sql = "delete from groupcomment where BoardID = ? and GroupCn = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BoardID);
			pstmt.setInt(2, GroupCn);

			int row = pstmt.executeUpdate();
			return row;

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close(); // pool conn 객체반환
		}

	}

	
	//총 게시물 구하는 함수 (페이징 처리에 필요)
	public int GroupTotalboardCount() throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			String sql = "select count(*) cnt from grouplist";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int totalcount = 0;
			if (rs.next()) {
				totalcount = rs.getInt("cnt");
			}
			return totalcount;
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

	}

	/* 동아리 상세보기 조회수 증가하는 함수 */
	
	//이건어때 잘했네 저거쓰자 응
	public boolean groupGetHit(int BoardId) throws SQLException {
		try {
			conn = datasource.getConnection();
			String sql = "update GroupList set hit=hit+1 where BoardID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BoardId);
			int row = pstmt.executeUpdate();

			if (row > 0) {
				return true;
			} else {
				return false;
			}

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		}
	} // comGetHit()

	/* 게시물 좋아요 증가하는 함수 */
	public boolean GroupGetLikeCount(int BoardID) throws SQLException {
		try {
			conn = datasource.getConnection();
			String sql = "update Grouplist set LikeCount=LikeCount+1 where BoardID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BoardID);
			int row = pstmt.executeUpdate();

			if (row > 0) {
				return true;
			} else {
				return false;
			}

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		}
	} // comGetLikeCount()
	
	
	
	//댓글 갯수 구하는 함수 
	public int commentCount() throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			String sql = "select (select count(*) from groupcomment where BoardID= a.BoardID) reply_count from grouplist a";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int totalcount = 0;
			if (rs.next()) {
				totalcount = rs.getInt("cnt");
			}
			return totalcount;
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

	}//commentCount() 끝
	
	
	
	
	  /* 동아리 랭크 리스트 목록 뿌려주기 */
	   public List<GroupDTO> GroupRanklist(int cpage, int pagesize, String select_category) throws Exception {

	      List<GroupDTO> Grouplist = null;
	      String StartDate = null;

	      Date d = new Date();
	     
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	      String sDate = sdf.format(d);
	      Date startDate = sdf.parse(sDate);
	      String dDay = null;
	      String where_sql = "";
	      
	      
			if(select_category != null){
				//빈값이 아닐때 조건 주기
				if(!"".equals(select_category)){
					where_sql = " and fieldcode = '"+select_category+"'";
				}
			}

	      try {
	         conn = datasource.getConnection();
	         String sql =  "select * from (select rownum rn, aa.* from ("
			   +"select BoardId,title,FieldCode,Place,hit,enddate,likecount,image "
	           +",(select count(*) from groupcomment b where BoardId = a.BoardId) replyCount "
			   +" from GroupList a where 1=1 "+where_sql+" order by likecount desc) aa) aaa "
			   +" where 1=1 and rn between ? and ?";

			int start = cpage * pagesize - (pagesize - 1);
			int end = cpage * pagesize;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);


	        System.out.println("sql::"+sql);
	
	        rs = pstmt.executeQuery();

	         // 객체 형태로 DB가지고 데이터
	         Grouplist = new ArrayList<GroupDTO>();
	         while (rs.next()) {
	            int boardId = rs.getInt("BoardId");
	            String Title = rs.getString("Title");
	            String FieldCode = rs.getString("FieldCode");
	            String Place = rs.getString("Place");
	            int hit = rs.getInt("hit");
	            int likecount = rs.getInt("likecount");

	            String endDate = rs.getString("EndDate");
	            Date endDate2 = sdf.parse(endDate);
	            String image = rs.getString("image");

	            long diff = endDate2.getTime() - startDate.getTime();
	            long diffDays = diff / (24 * 60 * 60 * 1000);
	            if (diffDays < 0) {
	               StartDate = "마감!";
	            } else {
	               dDay = Long.toString(diffDays);
	               StartDate = "D-" + dDay;
	            }

	            GroupDTO GroupListDTO = new GroupDTO();
	            GroupListDTO.setBoardId(boardId);
	            GroupListDTO.setFieldCode(FieldCode);
	            GroupListDTO.setTitle(Title);
	            GroupListDTO.setHit(hit);
	            GroupListDTO.setPlace(Place);
	            GroupListDTO.setStartDate(StartDate);
	            GroupListDTO.setLikeCount(likecount);
	            GroupListDTO.setImage(image);
//	            GroupDTO GroupListDTO = new GroupDTO(boardId, FieldCode, Title,
//	                  hit, Place, StartDate, likecount);
	            Grouplist.add(GroupListDTO);
	         }

	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         pstmt.close();
	         rs.close();
	         conn.close();
	         return Grouplist;
	      }
	   }
	   
	   /* 동아리 게시글 찜했는지 여부*/
	   public int GroupBoardLikeCheck(int boardid, int MemCode) throws Exception {

		  
	      try {
	    	  conn = datasource.getConnection();
				String sql = "select count(*) likecount from grouplike where boardid="+boardid+" and memcode = "+MemCode;
				pstmt = conn.prepareStatement(sql);
				//pstmt.setInt(1, boardid);
				//pstmt.setInt(2, MemCode);
				rs = pstmt.executeQuery();
				System.out.println("sql::"+sql);
				int likecount = 0;
				while (rs.next()) {
					likecount = rs.getInt("likecount");
				}
				System.out.println("likecount::"+String.valueOf(likecount));
				return likecount;
			} finally {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();
			}
	   }
	   
	   /* 동아리 게시글 찜하기*/
	   public int GroupBoardLike(int boardid, int MemCode) throws Exception {

		  
	      try {
	    	  conn = datasource.getConnection();
				String sql = "insert into GROUPLIKE(LikeID,BoardID,MemCode) values (likeid.nextval,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardid);
				pstmt.setInt(2, MemCode);
				int inserResult = pstmt.executeUpdate();
				int updateResult = 0;
				
				if(inserResult > 0){
					sql = "update Grouplist set LikeCount=LikeCount+1 where BoardID=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, boardid);
					updateResult = pstmt.executeUpdate();
				}
				
				return updateResult;
			} finally {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();
			}
	   }
	   
	   /* 동아리 게시글 찜하기*/
	   public int GroupBoardLikeDelete(int boardid, int MemCode) throws Exception {

	      try {
	    	  conn = datasource.getConnection();
				String sql = "delete from grouplike where boardid=? and memcode = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardid);
				pstmt.setInt(2, MemCode);
				int inserResult = pstmt.executeUpdate();
				int updateResult = 0;
				
				if(inserResult > 0){
					sql = "update Grouplist set LikeCount=LikeCount-1 where BoardID=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, boardid);
					updateResult = pstmt.executeUpdate();
				}
				
				return updateResult;
			} finally {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();
			}
	   }
	   
	   public int totalboardCount(String select_category) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = datasource.getConnection();
				String sql = "select count(*) cnt from grouplist ";
				if(select_category != null){
					if(!"".equals(select_category)){
						sql +=" where fieldcode = ?";
					}
				}
				
				pstmt = conn.prepareStatement(sql);
				
				if(select_category != null){
					if(!"".equals(select_category)){
						pstmt.setString(1, select_category);
					}
				}
				
				rs = pstmt.executeQuery();
				int totalcount = 0;
				if (rs.next()) {
					totalcount = rs.getInt("cnt");
				}
				return totalcount;
			} finally {
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException e) {
					}
				if (rs != null)
					try {
						rs.close();
					} catch (SQLException e) {
					}
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
					}
			}
		} // totalboardCount()
	/********************************* 동아리 끝 ************************/

}