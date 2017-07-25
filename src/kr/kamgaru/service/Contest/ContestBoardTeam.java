package kr.kamgaru.service.Contest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;
import kr.kamgaru.DTO.ContestDTO;

public class ContestBoardTeam implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		

		DAO dao = new DAO();
		System.out.println("***ContestListAction-TEST");
		int totalboardCount = dao.TeamTotalboardCount();
		
		//String boardid = request.getParameter("boardid");
		String psStr = request.getParameter("ps");    //pagesize
        String cpStr = request.getParameter("cp");    //currentpage
        
        if(psStr == null || psStr.trim().equals("")){
            psStr = "5"; // default 5건씩 
        }
        
        if(cpStr == null || cpStr.trim().equals("")){
            cpStr= "1";        //default 1 page
        }
      
        int pagesize = Integer.parseInt(psStr);
        int cpage = Integer.parseInt(cpStr);
        int pagecount = 0;                       
        
        System.out.println("ContestBoardTeam Action : "+ cpStr);
        System.out.println("ContestBoardTeam Action : "+ psStr);
        
        if(totalboardCount % pagesize==0){
            pagecount = totalboardCount/pagesize;
        }else{
            pagecount = (totalboardCount/pagesize) + 1;
        }
        List<ContestDTO> list= dao.contestTeam(cpage, pagesize);
        System.out.println("ContestBoardTeam "+list.size());
        ActionForward forward = new ActionForward();
        request.setAttribute("cpage", cpage);
        request.setAttribute("pagesize", pagesize);
        request.setAttribute("pagecount", pagecount);
        request.setAttribute("list", list);
        request.setAttribute("totalboardCount", totalboardCount);
        
        forward.setRedirect(false);
		forward.setPath("/ContestBoard/ContestBoardSubList.jsp");
		
		
		return forward;
	}

}
