/*
 * @ 프로젝트명 : 
 * @ 패키지명 : 
 * @ 파일명 : 
 * @ 작성자 : 
 * @ 작성일 : 
 * @ 설명 : 
 */

package kr.kamgaru.service.Admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;
import kr.kamgaru.DTO.AdminDTO;

public class AdminListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=UTF-8");
		
		DAO dao = new DAO();
		System.out.println("**ListAction OK");
		int totalboardCount = dao.totalboardCount();

		String psStr = request.getParameter("ps");    //pagesize
        String cpStr = request.getParameter("cp");    //currentpage

        if(psStr == null || psStr.trim().equals("")){
            //default 값
            psStr = "10"; // default 5건씩 
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
        //페이지 갯수 : 102 건 , pagesize :5   pagecount: 21
        
        List<AdminDTO> list= dao.requestList(cpage, pagesize);
		
        ActionForward forward = new ActionForward();
        request.setAttribute("cpage", cpage);
        request.setAttribute("pagesize", pagesize);
        request.setAttribute("pagecount", pagecount);
        request.setAttribute("list", list);
        request.setAttribute("totalboardCount", totalboardCount);

        forward.setRedirect(false);
		forward.setPath("/Admin/WriteRequestList.jsp");
		
		return forward;
	}

}
