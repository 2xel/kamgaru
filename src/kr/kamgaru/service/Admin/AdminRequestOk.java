package kr.kamgaru.service.Admin;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;
import kr.kamgaru.DTO.AdminDTO;

public class AdminRequestOk implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DAO dao = new DAO();
		System.out.println("**AdminRequestOk-TEST");

		String cateCode = request.getParameter("catecoryCode");
		int boardid = Integer.parseInt(request.getParameter("boardid"));
		System.out.println("**AdminRequestOk-TEST2");

		AdminDTO result = dao.requestOk(boardid, cateCode);
		System.out.println("**AdReqOK-list: " + result.getCategoryCode());
		ActionForward forward = new ActionForward();
        
        request.setAttribute("list", result);
        
        forward.setRedirect(false);
		forward.setPath("/Admin/WriteRequestOk.jsp");
		
		return forward;
	}

}
