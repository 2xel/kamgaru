package kr.kamgaru.service.Community;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;
import kr.kamgaru.DTO.CommunityDTO;

public class ComWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = new DAO();
		CommunityDTO dto = new CommunityDTO();
		
		//dto.setComCode(request.getParameter("ComCode"));
		//dto.setComCode(30);
		dto.setComTitle(request.getParameter("ComTitle"));
		dto.setComContents(request.getParameter("ComContents"));
		dto.setComImage(request.getParameter("ComImage"));
		//dto.setComImage("00");
		//System.out.println(request.getParameter("ComCode"));
		System.out.println(request.getParameter("ComTitle"));
		System.out.println(request.getParameter("ComContents"));
		System.out.println(request.getParameter("ComImage"));
		int result = dao.comWrite(dto);
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
		forward.setPath("/Community/CommunityList.jsp");
  
		return forward;
	}

}