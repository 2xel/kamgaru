/*
 * @ 프로젝트명 : 캠거루
 * @ 패키지명 : kr.kamgaru.service.Member;
 * @ 파일명 : MemberJoinAction
 * @ 작성자 : 이재민
 * @ 작성일 : 2017.4.25
 * @ 설명 : 비밀번호 찾기 페이지
 */

package kr.kamgaru.service.Member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.MemberDAO;
import kr.kamgaru.DTO.MemberDTO;
import kr.kamgaru.utils.MailInfo;
import kr.kamgaru.utils.MailSend;

public class MemberFindpwdAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		String email = request.getParameter("email");
		
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO rs = memberDAO.Findpwd(email);
		
		if(rs == null){
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('일치하는 회원정보가 없습니다');");
			out.println("location.href='http://localhost:8090/Kamgaru/Login/Findpwd.jsp';");
			out.println("</script>");
			out.close();
			System.out.println("비밀번호 찾기 일치하는 회원이 없음");
		}else{
			MailInfo mailInfo = new MailInfo(rs.getEmail(), rs.getJoincode()); // 메일 정보 
			mailInfo.setfindpwdContent(); //비밀번호 재설정 이메일 내용
			MailSend.send(mailInfo); // 이메일 전송 관련 static 메소드
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('이메일로 비밀번호 재설정하는 방법을 전송했습니다');");
			out.println("location.href='http://localhost:8090/Kamgaru/Login/Login.jsp';");
			out.println("</script>");
			out.close();
			System.out.println("비밀번호 찾기 이메일 전송완료");
		}
		return null;
	}
}
