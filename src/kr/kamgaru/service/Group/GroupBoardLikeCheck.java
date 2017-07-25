package kr.kamgaru.service.Group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;

public class GroupBoardLikeCheck implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request,
         HttpServletResponse response) throws Exception {
      // TODO Auto-generated method stub
      DAO dao = new DAO();
      
      int boardid= Integer.parseInt(request.getParameter("BoardID"));
      int MemCode= Integer.parseInt(request.getParameter("MemCode"));
      //
      int result = dao.GroupBoardLikeCheck(boardid, MemCode);
      
        
      ActionForward forward = new ActionForward();
      
      request.setAttribute("likecount", result);
        
      forward.setRedirect(false);
      //forward.setPath("GroupBoardSubList.jsp");
      
      return forward;
   }

}