package kr.kamgaru.service.Activity;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;
import kr.kamgaru.DTO.ActivityDTO;
import kr.kamgaru.DTO.Activityreply;

public class ActivityBoardTeam implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DAO dao = new DAO();
		
		String select_category = request.getParameter("select_category");
	    String select_order = request.getParameter("select_order");
		int totalboardCount = dao.ActivityTotalboardCount(select_category);
		String psStr = request.getParameter("ps"); 
		String cpStr = request.getParameter("cp"); 

		if (psStr == null || psStr.trim().equals("")) {
			psStr = "5";
		}

		if (cpStr == null || cpStr.trim().equals("")) {
			cpStr = "1"; 
		}

		int pagesize = Integer.parseInt(psStr);
		int cpage = Integer.parseInt(cpStr);
		int pagecount = 0;

		if (totalboardCount % pagesize == 0) {
			pagecount = totalboardCount / pagesize;
		} else {
			pagecount = (totalboardCount / pagesize) + 1;
		}
		
		//팀원 모집
		List<Activityreply> list = dao.ActivityTeam(cpage, pagesize);
		ActionForward forward = new ActionForward();

		request.setAttribute("list", list);
		request.setAttribute("totalboardCount", totalboardCount);
		  request.setAttribute("cpage", cpage);
	        request.setAttribute("pagesize", pagesize);
	        request.setAttribute("pagecount", pagecount);
		
		forward.setRedirect(false);
		
		forward.setPath("ActivityBoardSubList.jsp");

		return forward;
	}

}
