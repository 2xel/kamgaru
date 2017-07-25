package kr.kamgaru.service.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;


public class AdminManagerDelete implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=UTF-8");
		
		String nickname = request.getParameter("nickname");

		System.out.println("MemberDelete nickname : "+ nickname);
		
		if(nickname == null || nickname.trim().equals("")){
			response.sendRedirect("AdminMemberList.kam");
		}
		  
		DAO dao = new DAO();
		int result = dao.memberDelete(nickname);
		System.out.println("memDel-result : "+ result);
		if(result>0) {
			System.out.println("회원 삭제 성공");
			request.setAttribute("result", "success");
		}
		else {
			System.out.println("회원 삭제 실패");
			request.setAttribute("result", "fail");
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("AdminMemberList.kam");
  
		return forward;
	}
}