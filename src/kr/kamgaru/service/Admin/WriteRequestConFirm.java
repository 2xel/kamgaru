package kr.kamgaru.service.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;
import kr.kamgaru.DTO.AdminDTO;

public class WriteRequestConFirm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("WriteRequestConfirm ");
		DAO dao = new DAO();
		AdminDTO admDto = new AdminDTO();
		int result;
		
		admDto.setManager_name(request.getParameter("manager_name"));
		admDto.setManager_phone(request.getParameter("manager_phone"));
		admDto.setFieldCode(request.getParameter("fieldCode"));
		admDto.setCategoryCode(request.getParameter("categoryCode"));
		admDto.setTitle(request.getParameter("title"));
		admDto.setContents(request.getParameter("contents"));
		admDto.setStartDate(request.getParameter("start_date"));
		admDto.setEndDate(request.getParameter("end_date"));
		admDto.setWebsite(request.getParameter("website"));
		admDto.setImage(request.getParameter("attach_filename"));
		admDto.setPrizeMoney(request.getParameter("prizemoney"));
		admDto.setPlace(request.getParameter("place"));
		
		//String cateCode = request.getParameter("categoryCode");
		System.out.println("WriteRequestConfirmCategory "+request.getParameter("categoryCode"));
		System.out.println("WriteRequestConfirmFIELDCODE "+request.getParameter("fieldCode"));
		
		String boardid = request.getParameter("boardId");
		System.out.println("WriteRequestConfirmBoardid       "+boardid);
		
		if(request.getParameter("title")!=null){
			result = dao.requestUpdateConfirm(boardid,admDto);
		}else{
			result = dao.requestConfirm(boardid,admDto);
		}
		
		System.out.println("WriteRequestConfirm " + result);
		// System.out.println("list: "+result.getCategoryCode());
		ActionForward forward = new ActionForward();

		forward.setRedirect(false);
		forward.setPath("AdminListAction.kam");

		return forward;
	}

}
