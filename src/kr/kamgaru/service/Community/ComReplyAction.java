package kr.kamgaru.service.Community;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;

public class ComReplyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = new DAO();
		System.out.println("아아아ㅏ앙아아아ㅏ");
		
		String ComBoardID = request.getParameter("ComBoardID");
		String MemCode = request.getParameter("MemCode");
		String Contents = request.getParameter("Contents");
		Contents = URLDecoder.decode(Contents,"UTF-8");

		System.out.println("ComBoardIDComBoardIDComBoardID :::::" + ComBoardID);
		System.out.println("MemCodeMemCodeMemCode :::::" + MemCode);
		System.out.println("ContentsContentsContents ::::" + Contents);
		//서비스 객체 호출하고
		//insert 하고
		int result = dao.CommunityComment(ComBoardID, MemCode, Contents);
		System.out.println("result::::"+result);
		if(result>0) {
			request.setAttribute("result", "success");
		}
		else {
			request.setAttribute("result", "fail");
		}
	
		
		ActionForward forward = new ActionForward();
	    forward.setRedirect(false);
	   
		return null;
	}

}
