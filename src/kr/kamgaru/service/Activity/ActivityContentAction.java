package kr.kamgaru.service.Activity;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;
import kr.kamgaru.DTO.ActivityDTO;
import kr.kamgaru.DTO.Activityreply;
import kr.kamgaru.DTO.ContestDTO;




public class ActivityContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao=new DAO();
		 String select_category = request.getParameter("select_category");
	        String select_order = request.getParameter("select_order");
		int totalboardCount = dao.ActivityTotalboardCount(select_category);
	
		String psStr = request.getParameter("ps");  
        String cpStr = request.getParameter("cp");   
        String boardid= request.getParameter("boardid");
        
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
        //조회수 증가
        boolean updateSuccess = dao.ActivityGetHit(boardid);
        
        //게시글 1건뿌려줌
        ActivityDTO result= dao.ActivityBoardView(boardid);
	
        
		List<Activityreply> replyResult = dao.ActivityCommentlist(boardid, cpage, pagesize);
		System.out.println("list: "+result.getTitle()); 
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
