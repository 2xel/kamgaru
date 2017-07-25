package kr.kamgaru.service.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;

public class AdminBoardDelete implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=UTF-8");
		
		String categorycode = request.getParameter("categorycode");
		int boardid = Integer.parseInt(request.getParameter("boardid"));
		System.out.println("BoardDelete boardid : " + boardid + " catecode : " + categorycode);

		if(categorycode == null || categorycode.trim().equals("")) {
			response.sendRedirect("AdminListAction.kam");
		}
		
		DAO dao = new DAO();
		int result = dao.boardDelete(boardid, categorycode);
		System.out.println("boardDel-result : " + result);
		if(result > 0) {
			System.out.println("글 삭제 성공");
			request.setAttribute("result", "success");
		} else {
			System.out.println("글 삭제 실패");
			request.setAttribute("result", "fail");
		}
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("AdminListAction.kam");
		
		return forward;
	}

}






