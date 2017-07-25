/*
 * @ 프로젝트명 : 
 * @ 패키지명 : 
 * @ 파일명 : 
 * @ 작성자 : 
 * @ 작성일 : 
 * @ 설명 : 
 */

package kr.kamgaru.service.Contest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;
import kr.kamgaru.DTO.AdminDTO;
import kr.kamgaru.DTO.ContestDTO;

public class ContestListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DAO dao = new DAO();
		System.out.println("***ContestListAction-TEST");
		
		
		String psStr = request.getParameter("ps");    //pagesize
        String cpStr = request.getParameter("cp");    //currentpage

        String select_category = request.getParameter("select_category");
        String select_order = request.getParameter("select_order");
        int pageContestSize = 5; // 그룹당 페이지 수 정의
        
        int totalboardCount = dao.ContestTotalboardCount(select_category);
        
        if(psStr == null || psStr.trim().equals("")){
            psStr = "5"; // default 5건씩 
        }
        
        if(cpStr == null || cpStr.trim().equals("")){
            cpStr= "1";        //default 1 page
        }
      
        int pagesize = Integer.parseInt(psStr);
        int cpage = Integer.parseInt(cpStr);
        int pagecount = 0;                       
        int number=0;
        
        if(totalboardCount % pagesize==0){
            pagecount = totalboardCount/pagesize;
        }else{
            pagecount = (totalboardCount/pagesize) + 1;
        }
        number = totalboardCount - (cpage - 1) * pagesize; // 글목록에 표시할 글번호
        
        //페이지 그룹수 갯수 
        int pageGroupCount = totalboardCount /(pagesize*pageContestSize)+( totalboardCount % (pagesize*pageContestSize) == 0 ? 0 : 1);

        //페이지 그룹 번호
        int numPageContest = (int) Math.ceil((double)cpage/pageContestSize);
        
        List<ContestDTO> list= dao.contestList(cpage, pagesize,select_category,select_order);
		
        ActionForward forward = new ActionForward();
        request.setAttribute("cpage", cpage);
        request.setAttribute("pagesize", pagesize);
        request.setAttribute("pagecount", pagecount);
        request.setAttribute("pageContestSize", pageContestSize);
        request.setAttribute("pageGroupCount", pageGroupCount);
        request.setAttribute("numPageContest", numPageContest);
        request.setAttribute("list", list);
        request.setAttribute("totalboardCount", totalboardCount);
        request.setAttribute("select_category", select_category);
        request.setAttribute("select_order", select_order);
        
        forward.setRedirect(false);
		forward.setPath("/ContestBoard/ContestBoardList.jsp");
		
		return forward;
	}

}
