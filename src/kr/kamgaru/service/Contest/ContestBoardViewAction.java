package kr.kamgaru.service.Contest;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;
import kr.kamgaru.DTO.AdminDTO;
import kr.kamgaru.DTO.ContestDTO;

public class ContestBoardViewAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DAO dao = new DAO();
		
		int totalboardCount = dao.ContestTotalboardCount();

		String psStr = request.getParameter("ps");    //pagesize
        String cpStr = request.getParameter("cp");    //currentpage

        if(psStr == null || psStr.trim().equals("")){
            //default 값
            psStr = "5"; // default 5건씩 
        }
        
        if(cpStr == null || cpStr.trim().equals("")){
            cpStr= "1";        //default 1 page
        }
        int pagesize = Integer.parseInt(psStr);  //5
        int cpage = Integer.parseInt(cpStr);     //1
        int pagecount = 0;                       
		

		if (totalboardCount % pagesize == 0) { // 전체 건수 , pagesize >
			pagecount = totalboardCount / pagesize;
		} else {
			pagecount = (totalboardCount/pagesize) + 1;
        }
		
		System.out.println("**ContestBoardViewAction-TEST");

		
		
		int boardid = Integer.parseInt(request.getParameter("boardid"));
		System.out.println("**ContestBoardViewAction-TEST2");
		System.out.println("**ContestBoardViewAction-TEST2: "+boardid);
		
		ContestDTO result = dao.ContestBoardView(boardid);
		
		boolean updateSuccess = dao.contestGetHit(boardid);
		System.out.println("updateSuccess: "+updateSuccess);
		
		ArrayList<ContestDTO> comment =  dao.contestComment(boardid,cpage,pagesize);
		ActionForward forward = new ActionForward();
        
		request.setAttribute("boardid",String.valueOf(boardid));
        request.setAttribute("list", result);
        request.setAttribute("comment", comment);
        
        forward.setRedirect(false);
		forward.setPath("/ContestBoard/ContestBoardView.jsp");
		
		return forward;
	}
	

}
