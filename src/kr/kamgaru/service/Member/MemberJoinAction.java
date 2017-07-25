/*
 * @ 프로젝트명 : 캠거루
 * @ 패키지명 : kr.kamgaru.service.Member;
 * @ 파일명 : MemberJoinAction
 * @ 작성자 : 이재민
 * @ 작성일 : 2017.4.25
 * @ 설명 : 회원가입 페이지에 데이터를 DB에 넣어주는 Service
 */

package kr.kamgaru.service.Member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.MemberDAO;
import kr.kamgaru.utils.MailInfo;
import kr.kamgaru.utils.MailSend;
import kr.kamgaru.utils.RandomJoinCode;

public class MemberJoinAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String university = request.getParameter("university");
		String joincode = RandomJoinCode.getRandomJoinCode();
		
		System.out.println("회원가입진행중"+email);
		MemberDAO memberDAO = new MemberDAO();
		memberDAO.joinmember(email, pwd, name, nickname, university, joincode);
		
		MailInfo mailInfo = new MailInfo(email, joincode); // 메일 정보 
		mailInfo.setRegisterContent(); // 메일 설정 (인증)
		MailSend.send(mailInfo); // 이메일 전송 관련 static 메소드
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('회원가입 완료! 이메일 인증후 이용하실수 있습니다');");
		out.println("location.href='"+request.getContextPath()+"/Login/Login.jsp';");
		out.println("</script>");
		out.close();
		
		
		return null;
	}
}
