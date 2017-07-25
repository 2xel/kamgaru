package kr.kamgaru.service.Activity;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;
import kr.kamgaru.DTO.ActivityDTO;
import kr.kamgaru.DTO.Activityreply;


public class ActivityBoardViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DAO dao = new DAO();
		
		int totalboardCount = dao.totalboardCount();
		
		String psStr = request.getParameter("ps");  
        String cpStr = request.getParameter("cp");  
        
        if(psStr == null || psStr.trim().equals("")){
          
        	psStr = "5"; 
        }
        
        if(cpStr == null || cpStr.trim().equals("")){
            cpStr= "1";        
        }
      
        int pagesize = Integer.parseInt(psStr); 
        int cpage = Integer.parseInt(cpStr);    
        int pagecount = 0;                       
        
        if(totalboardCount % pagesize==0){     
            pagecount = totalboardCount/pagesize;
        }else{
            pagecount = (totalboardCount/pagesize) + 1;
        }
        
        
        String boardid = request.getParameter("boardid");
        System.out.println("view board: "+request.getParameter("boardid"));
        ActivityDTO result= dao.ActivityBoardView(boardid);
		
        //게시판 댓글 여러건 뿌려줌
		List<Activityreply> replyResult = dao.ActivityCommentlist(boardid, cpage, pagesize);
		
        ActionForward forward = new ActionForward();
        
        request.setAttribute("boardid", String.valueOf(boardid));
        request.setAttribute("dto", result);
        request.setAttribute("replyResult", replyResult);
        request.setAttribute("cpage", cpage);
        request.setAttribute("pagesize", pagesize);
        request.setAttribute("pagecount", pagecount);
        request.setAttribute("totalboardCount", totalboardCount);
        
        forward.setRedirect(false);
      forward.setPath("ActivityBoardView.jsp");
      
      return forward; 
	}

}
