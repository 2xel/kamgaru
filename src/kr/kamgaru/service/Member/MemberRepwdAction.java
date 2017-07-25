/*
 * @ 프로젝트명 : 캠거루
 * @ 패키지명 : kr.kamgaru.service.Member;
 * @ 파일명 : MemberRepwdAction
 * @ 작성자 : 이재민
 * @ 작성일 : 2017.4.29
 * @ 설명 : 비밀번호 찾기 -> 비밀번호 재설정메일에서 비밀번호를 새롭게 바꿈
 */

package kr.kamgaru.service.Member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.MemberDAO;
import kr.kamgaru.DTO.MemberDTO;

public class MemberRepwdAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		String email = request.getParameter("email");
		String joincode = request.getParameter("joincode");

		MemberDAO memberDAO = new MemberDAO();
		
		MemberDTO rs = memberDAO.repwd(email, joincode);
		
		if(rs==null){
			System.out.println("비정상적인 비밀번호변경 요청");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비정상적인 요청입니다');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}else{
			ActionForward forward = new ActionForward();
			System.out.println("정상적인 비밀번호 찾기 비밀번호 번경 요청");
			HttpSession session = request.getSession();
			session.setAttribute("memcode", rs.getMemcode());
			forward.setRedirect(true);
			forward.setPath(request.getContextPath()+"/Login/FindRepwd.jsp");
			return forward;
		}
		
	}

}
