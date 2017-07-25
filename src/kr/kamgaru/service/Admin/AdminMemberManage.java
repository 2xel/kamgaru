package kr.kamgaru.service.Admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;
import kr.kamgaru.DTO.MemberDTO;

public class AdminMemberManage implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		DAO dao = new DAO();
		int totalMemberCount = dao.totalMemberCount();
		
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
        
        if(totalMemberCount % pagesize==0){        //전체 건수 , pagesize > 
            pagecount = totalMemberCount/pagesize;
        }else{
            pagecount = (totalMemberCount/pagesize) + 1;
        }
        //페이지 갯수 : 102 건 , pagesize :5   pagecount: 21
        

        
        List<MemberDTO> list= dao.memberList(cpage, pagesize);
		
        
        ActionForward forward = new ActionForward();
        request.setAttribute("cpage", cpage);
        request.setAttribute("pagesize", pagesize);
        request.setAttribute("pagecount", pagecount);
        request.setAttribute("list", list);
        request.setAttribute("totalboardCount", totalMemberCount);
        
        forward.setRedirect(false);
		forward.setPath("/Admin/MemberManage.jsp");
		
		return forward;
	}
}
