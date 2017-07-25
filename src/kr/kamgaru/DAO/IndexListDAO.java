package kr.kamgaru.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.kamgaru.DTO.IndexDTO;

public class IndexListDAO {
	static DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	static {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			Context envCtx = (Context) ctx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("/jdbc/oracle");
		} catch (NamingException e) {
			System.out.println("lookup Fail : " + e.getMessage());
		}
	}
	
	//1. 동아리 Index list 출력
	public ArrayList<IndexDTO> Groupindexlist() throws SQLException{
		ArrayList<IndexDTO> indexdtolist = new ArrayList<>();
		try {	
			conn = ds.getConnection();
			String sql = "select * from(select rownum rn, boardid, groupname,title,contents,enddate,image,hit, enrolldate from(select * from grouplist order by enrolldate desc)) where rn between 1 and 6";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				IndexDTO indexdto = new IndexDTO();
				indexdto.setBoardid(rs.getString("boardid"));
				indexdto.setGroupname(rs.getString("groupname"));
				indexdto.setTitle(rs.getString("title"));
				indexdto.setContents(rs.getString("title"));
				indexdto.setEnddate(rs.getString("enddate"));
				indexdto.setImage(rs.getString("image"));
				indexdto.setHit(rs.getString("hit"));
				indexdtolist.add(indexdto);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return indexdtolist;
	}
	
	
	
	//2. 대외활동 Index list 출력
	public ArrayList<IndexDTO> ActivityIndexList() throws SQLException{
		ArrayList<IndexDTO> indexdtolist = new ArrayList<>();
		try {	
			conn = ds.getConnection();
			String sql = "select * from(select rownum rn, place,title,boardid,enddate,hit, enrolldate from(select * from activitylist order by enrolldate desc)) where rn between 1 and 6";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				IndexDTO indexdto = new IndexDTO();
				indexdto.setPlace(rs.getString("place"));
				indexdto.setTitle(rs.getString("title"));
				indexdto.setBoardid(rs.getString("boardid"));
				indexdto.setEnddate(rs.getString("enddate"));
				indexdto.setHit(rs.getString("hit"));
				indexdtolist.add(indexdto);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return indexdtolist;
	}
	
	
	//3. 공모전 Index list 출력
	public ArrayList<IndexDTO> ContestIndexList() throws SQLException{
		ArrayList<IndexDTO> indexdtolist = new ArrayList<>();
		try {	
			conn = ds.getConnection();
			String sql = "select * from(select rownum rn, place,title,boardid,enddate,hit, enrolldate from(select * from contestlist order by enrolldate desc)) where rn between 1 and 6";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				IndexDTO indexdto = new IndexDTO();
				indexdto.setPlace(rs.getString("place"));
				indexdto.setTitle(rs.getString("title"));
				indexdto.setBoardid(rs.getString("boardid"));
				indexdto.setEnddate(rs.getString("enddate"));
				indexdto.setHit(rs.getString("hit"));
				indexdtolist.add(indexdto);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return indexdtolist;
	}
	
	
	
	//4. 커뮤니티 Index list 출력
	public ArrayList<IndexDTO> CommunityIndexList() throws SQLException{
		ArrayList<IndexDTO> indexdtolist = new ArrayList<>();
		try {	
			conn = ds.getConnection();
			String sql = "select * from(select rownum rn, comcode,comtitle,comcontents,comimage from(select * from communityboard order by comcode asc)) where rn between 1 and 6";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				IndexDTO indexdto = new IndexDTO();
				indexdto.setComcode(rs.getString("comcode"));
				indexdto.setComtitle(rs.getString("comtitle"));
				indexdto.setComcontents(rs.getString("comcontents"));
				indexdto.setComimage(rs.getString("comimage"));
				indexdtolist.add(indexdto);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return indexdtolist;
	}
	
}