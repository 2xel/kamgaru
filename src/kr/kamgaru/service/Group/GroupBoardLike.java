package kr.kamgaru.service.Group;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;
import kr.kamgaru.DTO.GroupDTO;

public class GroupBoardLike implements Action {

	   @Override
	   public ActionForward execute(HttpServletRequest request,
	         HttpServletResponse response) throws Exception {
	      // TODO Auto-generated method stub
	      DAO dao = new DAO();
	      
	      int boardid= Integer.parseInt(request.getParameter("BoardID"));
	      int MemCode= Integer.parseInt(request.getParameter("MemCode"));
	      String type = request.getParameter("type"); //insert , delete
	      int result = 0;
	      
	    //동아리 게시글 좋아요
	      if("insert".equals(type)){
	    	  result = dao.GroupBoardLike(boardid, MemCode);
	      }else {
	    	  //delete
	    	  result = dao.GroupBoardLikeDelete(boardid, MemCode);
	      }
	        
	      ActionForward forward = new ActionForward();
	      //성공 1 실패 0
	      request.setAttribute("result", result);
	        
	      forward.setRedirect(false);
	      //forward.setPath("GroupBoardSubList.jsp");
	      
	      return forward;
	   }

	}