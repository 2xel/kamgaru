package kr.kamgaru.service.Activity;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;
import kr.kamgaru.DTO.ActivityDTO;
import kr.kamgaru.DTO.AdminDTO;
import kr.kamgaru.DTO.ContestDTO;

public class ActivityTeamContent implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//DAO ActivityReply = new DAO();
		
		DAO dao = new DAO();
		
		String select_category = request.getParameter("select_category");
	    String select_order = request.getParameter("select_order");
	     //댓글   
	   // String BoardID = (String)request.getParameter("boardid");
		
	    String BoardID = request.getParameter("BoardID");
	    System.out.println("boardid2222: "+request.getParameter("BoardID"));
	    String MemCode = request.getParameter("MemCode");
	    System.out.println("이것도: "+request.getParameter("MemCode"));
		String Contents = request.getParameter("Contents");
		System.out.println(request.getParameter("Contents"));
	        
		int totalboardCount = dao.ActivityTotalboardCount(select_category);

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
		

		if (totalboardCount % pagesize == 0) { 
			pagecount = totalboardCount / pagesize;
		} else {
			pagecount = (totalboardCount/pagesize) + 1;
        }
		

		System.out.println("Teamac: "+ BoardID);

		ActivityDTO result = dao.ActivityBoardView(BoardID);
		System.out.println("**AdReqOK-list: " + result.getBoardId());
		
		
		
		ActionForward forward = new ActionForward();
        
        request.setAttribute("list2", result);
        request.setAttribute("dto", result);
        request.setAttribute("boardid ", BoardID );
        
        forward.setRedirect(false);
		forward.setPath("/ActivityBoard/ActivityBoardView.jsp?boardid="+BoardID);
		
		return forward;
	}
	

}
