package kr.kamgaru.service.Activity;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;
import kr.kamgaru.DTO.ActivityDTO;

public class ActivityListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DAO dao = new DAO();
		
	    String select_category = request.getParameter("select_category");
        String select_order = request.getParameter("select_order");
        
        
		int totalboardCount = dao.ActivityTotalboardCount(select_category);
		
		
		System.out.println("dao: " + dao);
		System.out.println("totalboardCount: " + totalboardCount);
		
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
        
        
        List<ActivityDTO> list = dao.Activitylist(cpage, pagesize,select_category, select_order);
        
        ActionForward forward = new ActionForward();
        request.setAttribute("cpage", cpage);
        request.setAttribute("pagesize", pagesize);
        request.setAttribute("pagecount", pagecount);
        request.setAttribute("list", list);
        request.setAttribute("totalboardCount", totalboardCount);
        request.setAttribute("select_category", select_category);
        request.setAttribute("select_order", select_order);
        
        
        forward.setRedirect(false);
		forward.setPath("ActivityBoardList.jsp");
		
		return forward;
	}

}