package kr.kamgaru.service.Activity;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;


public class ActivityReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		DAO ActivityReply = new DAO();

		System.out.println("BoardID re ac: "+request.getParameter("BoardID"));
		
		String BoardID = (String)request.getParameter("BoardID");
		String MemCode = request.getParameter("MemCode");
		
		System.out.println("memcode re ac: "+request.getParameter("MemCode"));
		String Contents = request.getParameter("Contents");
		
		Contents = URLDecoder.decode(Contents,"UTF-8");
		System.out.println("Contents:::"+Contents);
		System.out.println("BoardID: "+request.getParameter("BoardID"));

		
		int result = ActivityReply.ActivityComment( BoardID, MemCode,  Contents);
		System.out.println("result::::"+result);
		if(result>0) {
			request.setAttribute("result", "success");
		}
		else {
			request.setAttribute("result", "fail");
		}
		
		request.setAttribute("boardid ", BoardID );
		System.out.println("BoardID: "+BoardID);
		
		ActionForward forward = new ActionForward();
	    
	    forward.setRedirect(false);
		
		return null;
	}

}
