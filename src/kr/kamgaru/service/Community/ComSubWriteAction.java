package kr.kamgaru.service.Community;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;

public class ComSubWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = new DAO();
		System.out.println("희ㅢ희르히ㅢ힇");
		
		String ComCode = request.getParameter("ComCode");
		String MemCode =request.getParameter("MemCode");
		String Title = request.getParameter("Title");

		
		System.out.println("ComCodeComCodeComCode:::" + ComCode);
		System.out.println("MemCodeMemCodeMemCode:::" + MemCode);
		System.out.println("TitleTitleTitle:::" + Title);		
	
		
		int result = dao.comSubWrite(ComCode, MemCode, Title);
		System.out.println(result); 
		
		if(result>0) {
			System.out.println("글 입력 성공");
			request.setAttribute("result", "success");
		}
		else {
			System.out.println("글 입력 실패");
			request.setAttribute("result", "fail");
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/Community/CommunitySubList.jsp");
  
		return forward;
	}

}