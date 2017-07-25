package kr.kamgaru.service.Community;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;
import kr.kamgaru.DTO.CommunityDTO;

public class ComSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = new DAO();
		System.out.println("search들어왔니");
		
		String title = request.getParameter("title");
		List<CommunityDTO> list = dao.comSearch(title);
		System.out.println(list.size() + "list.size 찍힐거야?????");

		ActionForward forward = new ActionForward();
		
		request.setAttribute("list", list);
		forward.setRedirect(false);
		forward.setPath("/Community/CommunityList.jsp");

		return forward;
	}

}
