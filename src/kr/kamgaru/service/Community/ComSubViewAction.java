package kr.kamgaru.service.Community;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;
import kr.kamgaru.DTO.CommunityReplyDTO;

public class ComSubViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = new DAO();
		System.out.println("subview 들어왔니?");

		/*
		 * int communityCN = Integer.parseInt(request.getParameter("communityCN")); 
		 * int ComBoardId = Integer.parseInt(request.getParameter("ComBoardID"));
		 * 
		 * List<CommunityReplyDTO> replyResult = dao.comReplyGetList(communityCN); 
		 * List<CommunityReplyDTO> replyResult = dao.comCommentlist(ComBoardId);
		
		 */

		String comboardid =request.getParameter("comboardid");
		System.out.println("comboardid" + comboardid);

		// 1.조회수 업데이트
		boolean updateSuccess = dao.comGetHit(comboardid);
		System.out.println("updateSuccess: " + updateSuccess);

		// 2. 댓글 가져오기
		List<CommunityReplyDTO> replyResult = dao.comCommentlist(comboardid);
		ActionForward forward = new ActionForward();
		
		request.setAttribute("comboardid", comboardid);
		request.setAttribute("replyResult", replyResult);
		
		/* request.setAttribute("replyResult", replyResult); */

		forward.setRedirect(false);
		forward.setPath("CommunitySubView.jsp");

		return forward;
	}

}