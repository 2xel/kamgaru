package kr.kamgaru.service.Community;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;

public class ComDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DAO dao = new DAO();
		
		int comBoardId = Integer.parseInt(request.getParameter("ComBoardID"));
		System.out.println(comBoardId + "comBoardId찎힐래?");
		
		/*if(comBoardId == null || comBoardId.trim().equals("")){
			response.sendRedirect("CommunitySubList.kam");
		}*/
		
		int result = dao.comdelete(comBoardId);
		
		if(result>0) {
			System.out.println("글 삭제 성공");
			request.setAttribute("result", "success");
		}
		else {
			System.out.println("글 삭제 실패");
			request.setAttribute("result", "fail");
		}
		
		request.setAttribute("comboardid", comBoardId);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("CommunitySubList.kam");
  
		return forward;
		
	}

}
