package kr.kamgaru.service.Member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.MemberDAO;

public class MemberRepwdOkAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		String memcode = request.getParameter("memcode");
		String pwd = request.getParameter("pwd");
		System.out.println(memcode + "/" + pwd);
		
		MemberDAO memberDAO = new MemberDAO();
		int result = memberDAO.repwdok(memcode, pwd);
		
		if(result!=0){
			System.out.println("비밀번호 재설정완료");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 재설정 완료');");
			out.println("location.href='"+request.getContextPath()+"/Login/Login.jsp';");
			out.println("</script>");
			out.close();
		}else{
			System.out.println("비밀번호 재설정 실패");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 재설정 실패');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}

		
		return null;
	}

}
