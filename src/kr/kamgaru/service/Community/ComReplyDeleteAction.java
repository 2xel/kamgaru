package kr.kamgaru.service.Community;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;

public class ComReplyDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = new DAO();
		
		int ComBoardID = Integer.parseInt(request.getParameter("ComBoardID"));
		int CommunityCn = Integer.parseInt(request.getParameter("CommunityCn"));
		

		//서비스 객체 호출하고
		//insert 하고
		int result = dao.CommunityCommentDelete(ComBoardID, CommunityCn);
		
		System.out.println("result::::"+result);
		if(result>0) {
			request.setAttribute("result", "success");
		}
		else {
			request.setAttribute("result", "fail");
		}
		
		
		ActionForward forward = new ActionForward();
	    forward.setRedirect(false);	     
		
		return forward;
	}
	

}
