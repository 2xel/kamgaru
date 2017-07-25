package kr.kamgaru.service.Member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.MemberDAO;

public class MemberMypageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");

		String memcode = request.getParameter("memcode");
		String oldpwd = request.getParameter("oldpwd");
		String pwd = request.getParameter("pwd");
		String university = request.getParameter("university");
		
		MemberDAO memberDAO = new MemberDAO();
		
		int rowcount = memberDAO.mypage(memcode, oldpwd, pwd, university);
		
		if(rowcount==1){
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원정보 수정완료');");			
			out.println("location.href='"+request.getContextPath()+"/index.jsp';");
			out.println("</script>");
			out.close();
		}else{
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호를 다시 확인하세요');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		
		return null;
	}

}
