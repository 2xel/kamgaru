/*
 * @ 프로젝트명 : 캠거루
 * @ 패키지명 : kr.kamgaru.service.Member;
 * @ 파일명 : MemberLoginAction
 * @ 작성자 : 이재민
 * @ 작성일 : 2017.4.27
 * @ 설명 : 로그인을 처리해주는 곳 email을 DAO로 전송한후 멤버코드,비밀번호,이메일 인증여부를 확인한후 로그인 처리해줌
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

public class MemberLoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");

		MemberDAO memberDAO = new MemberDAO();
		
		MemberDTO rs = memberDAO.login(email);
		
		if(rs==null){
			System.out.println("가입되어있지 않은 회원");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디와 암호를 다시한번 확인해주세요');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}else if(pwd.equals(rs.getPwd())){			
			if(rs.getJoincheck()==true){
				ActionForward forward = new ActionForward();
				System.out.println("인증회원 로그인 성공");
				HttpSession session = request.getSession();
				session.setAttribute("memcode", rs.getMemcode());
				session.setAttribute("nickname", rs.getNickname());
				forward.setRedirect(true);
				forward.setPath(request.getContextPath()+"/index.jsp");
				return forward;
			}else{
				System.out.println("비인증회원 이메일/암호는 일치");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('이메일 인증후 이용하실 수 있습니다');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				return null;
			}
		}else{
			System.out.println("암호불일치회원");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디와 암호를 다시한번 확인해주세요');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
	}
}