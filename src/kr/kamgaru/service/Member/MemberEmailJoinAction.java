/*
 * @ 프로젝트명 : 캠거루
 * @ 패키지명 : kr.kamgaru.service.Member;
 * @ 파일명 : MemberEmailJoinAction
 * @ 작성자 : 이재민
 * @ 작성일 : 2017.4.26
 * @ 설명 : 이메일로 보내준 랜덤값을 받아서 서버와 맞는지 확인
 */

package kr.kamgaru.service.Member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.MemberDAO;
import kr.kamgaru.DTO.MemberDTO;

public class MemberEmailJoinAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		String email = request.getParameter("email");
		String joincode = request.getParameter("joincode");
		
		System.out.println("비밀번호 재설정 페이지 이메일 요청이 들어옴");
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO(email, joincode);
		memberDAO.joincheckupdate(memberDTO);
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('이메일 인증 완료');");
		out.println("location.href='http://localhost:8090/Kamgaru/Login/Login.jsp';");
		out.println("</script>");
		out.close();
		
		return null;
	}
}
