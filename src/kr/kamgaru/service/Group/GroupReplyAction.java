package kr.kamgaru.service.Group;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;


public class GroupReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		DAO GroupReply = new DAO();
		
		//int GroupCN = Integer.parseInt(request.getParameter("GroupCn"));
		int BoardID = Integer.parseInt(request.getParameter("BoardID"));
		int MemCode = Integer.parseInt(request.getParameter("MemCode"));
		String Contents = request.getParameter("Contents");
		Contents = URLDecoder.decode(Contents,"UTF-8");
		System.out.println("Contents:::"+Contents);

		//서비스 객체 호출하고
		//insert 하고
		int result = GroupReply.GroupComment( BoardID, MemCode,  Contents);
		System.out.println("result::::"+result);
		if(result>0) {
			request.setAttribute("result", "success");
		}
		else {
			request.setAttribute("result", "fail");
		}
		
		//request.setAttribute("GroupCN", GroupCN);
		
		ActionForward forward = new ActionForward();
	    
	    forward.setRedirect(false);
	    //forward.setPath("GroupBoardView.jsp");
	     
		
		return forward;
	}

}
