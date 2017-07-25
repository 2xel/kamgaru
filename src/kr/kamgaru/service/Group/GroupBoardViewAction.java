package kr.kamgaru.service.Group;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;
import kr.kamgaru.DTO.GroupDTO;
import kr.kamgaru.DTO.GroupReplyDTO;



public class GroupBoardViewAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request,
         HttpServletResponse response) throws Exception {
      // TODO Auto-generated method stub
      DAO dao=new DAO();
        int boardid= Integer.parseInt(request.getParameter("boardid"));
    
        //1.조회수 업데이트        
        boolean updateSuccess = dao.groupGetHit(boardid);
        
        //2.뷰 상세내용을 가져온다.
      GroupDTO result= dao.GroupBoardView(boardid);
      
      //3.뷰의 댓글을 가져온다.
      List<GroupReplyDTO> replyResult = dao.GroupCommentlist(boardid);
      
        ActionForward forward = new ActionForward();
        
        request.setAttribute("groupdto", result);
        request.setAttribute("replyResult", replyResult);
        request.setAttribute("boardid", String.valueOf(boardid));
      
        forward.setRedirect(false);
        forward.setPath("GroupBoardView.jsp");
      
      return forward; 
   }

}