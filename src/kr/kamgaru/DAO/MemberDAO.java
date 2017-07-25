package kr.kamgaru.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.kamgaru.DTO.MemberDTO;

public class MemberDAO {
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
	//1. 회원가입
	public void joinmember(String email,String pwd,String name,String nickname,String university, String joincode) throws Exception{
		try {
			conn = ds.getConnection();
			String sql = "insert into memberinfo(memcode,email,pwd,name,nickname,university,joincode,joincheck) values(membercode.nextval,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, nickname);
			pstmt.setString(5, university);
			pstmt.setString(6, joincode);
			pstmt.setBoolean(7, false);
			pstmt.executeQuery();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		
	}
	
	//2.이메일 중복검사
	public int emailduplicationcheck(String email) throws Exception{
		int rowcount = 0;
		try {
			conn = ds.getConnection();
			String sql = "select memcode from memberinfo where email=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			rowcount=pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return rowcount;
	}
	
	//3.닉네임 중복검사
	public int nicknameduplicationcheck(String nickname) throws Exception{
		int rowcount = 0;
		try {
			conn = ds.getConnection();
			String sql = "select memcode from memberinfo where nickname=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, nickname);
			rowcount=pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return rowcount;
	}
	
	//4.인증메일 보낸 내용에서 인증확인시 회원인증완료 시키는 DAO
	public int joincheckupdate(MemberDTO memberDTO) throws Exception{
		int rowcount = 0;
		try {
			conn = ds.getConnection();
			String sql = "update memberinfo set joincheck=? where email=? and JOINCODE=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setBoolean(1, true);
			pstmt.setString(2, memberDTO.getEmail());
			pstmt.setString(3, memberDTO.getJoincode());

			rowcount=pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return rowcount;
	}
	
	//4.로그인
	public MemberDTO login(String email) throws Exception{
		MemberDTO memberDTO = null;
		try {
			conn = ds.getConnection();
			String sql = "select memcode, nickname, pwd, university, joincheck from memberinfo where email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				memberDTO = new MemberDTO();
				memberDTO.setMemcode(rs.getString("memcode"));
				memberDTO.setPwd(rs.getString("pwd"));
				memberDTO.setJoincheck(rs.getBoolean("joincheck"));
				memberDTO.setUniversity(rs.getString("university"));
				memberDTO.setNickname(rs.getString("nickname"));
			}
		} catch (Exception e) {
			memberDTO = null;
		} finally{
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return memberDTO;
	}
	
	//5. 비밀번호 찾기
	public MemberDTO Findpwd(String email) throws Exception{
		MemberDTO memberDTO = null;
		try {
			conn = ds.getConnection();
			String sql = "select joincode, email from memberinfo where email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if(rs.next()){
				memberDTO = new MemberDTO();
				memberDTO.setEmail(rs.getString("email"));
				memberDTO.setJoincode(rs.getString("joincode"));
			}
		} catch (Exception e) {
			memberDTO = null;
		} finally{
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return memberDTO;
	}
	
	//6. 비밀번호 재설정 페이지 이동
	public MemberDTO repwd(String email, String joincode) throws Exception{
		MemberDTO memberDTO = null;
		try {
			conn = ds.getConnection();
			String sql = "select memcode from memberinfo where email=? and joincode=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, joincode);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				memberDTO = new MemberDTO();
				memberDTO.setMemcode(rs.getString("memcode"));
			}
		} catch (Exception e) {
			memberDTO = null;
		} finally{
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return memberDTO;
	}
	
	//7. 비밀번호 재설정 완료
	public int repwdok(String memcode, String pwd) throws Exception{
		int rowcount = 0;
		try {
			conn = ds.getConnection();
			String sql = "update memberinfo set pwd=? where memcode=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pwd);
			pstmt.setString(2, memcode);
			rowcount = pstmt.executeUpdate();
	
		} catch (Exception e) {
		} finally{
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return rowcount;
	}
	public int mypage(String memcode, String oldpwd, String pwd, String university) throws Exception{
		int rowcount = 0;
		try {
			conn = ds.getConnection();
			String sql = "update memberinfo set pwd=?, university=? where memcode=? and pwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pwd);
			pstmt.setString(2, university);
			pstmt.setString(3, memcode);
			pstmt.setString(4, oldpwd);
			rowcount = pstmt.executeUpdate();
	
		} catch (Exception e) {
			rowcount = 0;
		} finally{
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return rowcount;
	}
}