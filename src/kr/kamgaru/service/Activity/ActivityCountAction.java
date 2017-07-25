package kr.kamgaru.service.Activity;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;
import kr.kamgaru.DTO.ActivityDTO;
import kr.kamgaru.DTO.Activityreply;
import kr.kamgaru.DTO.GroupDTO;
import kr.kamgaru.DTO.GroupReplyDTO;



public class ActivityCountAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DAO dao=new DAO();
        String boardid= request.getParameter("boardid");
        
        
        boolean updateSuccess = dao.ActivityGetHit(boardid);
        
		
        ActionForward forward = new ActionForward();
        
   
        request.setAttribute("boardid", String.valueOf(boardid));
      
        forward.setRedirect(false);
        forward.setPath("ActivityBoardView.jsp");
      
      return forward; 
	}

}
