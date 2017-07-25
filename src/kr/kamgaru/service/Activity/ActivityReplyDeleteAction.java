package kr.kamgaru.service.Activity;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;

public class ActivityReplyDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception {

		DAO ActivityReply = new DAO();
		System.out.println("삭제"+request.getParameter("BoardID"));
		String BoardID = request.getParameter("BoardID");
		String ActivityCn = request.getParameter("ActivityCn"); //여기서부터
		System.out.println("actiL: "+request.getParameter("ActivityCn")); //찾았다

		//서비스 객체 호출하고
		//insert 하고
		int result = ActivityReply.ActivityCommentDelete(BoardID, ActivityCn);
		System.out.println("result::::"+result);
		if(result>0) {
			request.setAttribute("result", "success");
		}
		else {
			request.setAttribute("result", "fail");
		}
		
		ActionForward forward = new ActionForward();
	    
	    forward.setRedirect(false);
	    //forward.setPath("ActivityBoardView.jsp");
	     
		
		return null;
	}

}
