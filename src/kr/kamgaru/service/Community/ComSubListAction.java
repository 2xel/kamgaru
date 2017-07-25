package kr.kamgaru.service.Community;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;
import kr.kamgaru.DTO.CommunityDTO;

public class ComSubListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = new DAO();
		System.out.println("***Action > ComSubListAction-TEST");

		CommunityDTO dto = new CommunityDTO();
		
		List<CommunityDTO> list = dao.comGetView(dto);

		ActionForward forward = new ActionForward();
		
		request.setAttribute("list", list);

		forward.setRedirect(false);
		forward.setPath("CommunitySubList.jsp");

		return forward;
	}

}