package kr.kamgaru.service.Contest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;

public class ContestBoardReplyDelete implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		DAO Reply = new DAO();
		
		//int GroupCN = Integer.parseInt(request.getParameter("GroupCn"));
		
		String BoardID = request.getParameter("BoardID");
		String contestCN = request.getParameter("contestCN");
		
		System.out.println("BoardID: "+ BoardID+"  ContestCn: "+contestCN);
		//서비스 객체 호출하고
		//insert 하고
		int result = Reply.ContestCommentDelete(BoardID, contestCN);
		System.out.println("result::::"+result);
		if(result>0) {
			request.setAttribute("result", "success");
		}
		else {
			request.setAttribute("result", "fail");
		}
		
		
		ActionForward forward = new ActionForward();
	    
	    forward.setRedirect(false);
	    //forward.setPath("GroupBoardView.jsp");
	     
		
		return null;
	}

}
