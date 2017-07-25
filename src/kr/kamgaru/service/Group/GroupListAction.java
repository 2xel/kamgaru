package kr.kamgaru.service.Group;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;
import kr.kamgaru.DTO.GroupDTO;

public class GroupListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		DAO dao = new DAO();
		
		String select_category = request.getParameter("select_category");
        String select_order = request.getParameter("select_order");
		
        // 전체 글의 수
		int totalboardCount = dao.totalboardCount(select_category);
		
		System.out.println("totalboardCount: " + totalboardCount);
		
		int pageGroupSize = 5; // 그룹당 페이지 수 정의
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
        int number = 0;
        
        if(totalboardCount % pagesize==0){        //전체 건수 , pagesize > 
            pagecount = totalboardCount/pagesize;
        }else{
            pagecount = (totalboardCount/pagesize) + 1;
        }
        
        System.out.println("pagesize:"+pagesize +",cpage:"+cpage+",pagecount:"+pagecount);
        
        //페이지 갯수 : 102 건 , pagesize :5   pagecount: 21
        //이까지 이해가?? 응 여기까진 ㅇㅇ
        
        number = totalboardCount - (cpage - 1) * pagesize; // 글목록에 표시할 글번호
        
        //페이지 그룹수 갯수 
        int pageGroupCount = totalboardCount /(pagesize*pageGroupSize)+( totalboardCount % (pagesize*pageGroupSize) == 0 ? 0 : 1);

        //페이지 그룹 번호
        int numPageGroup = (int) Math.ceil((double)cpage/pageGroupSize);
        
        List<GroupDTO> Grouplist = dao.Grouplist(cpage, pagesize, select_category, select_order);
		System.out.println("확인: "+Grouplist.size());
        
        ActionForward forward = new ActionForward();
        request.setAttribute("cpage", cpage);
        request.setAttribute("pagesize", pagesize);
        request.setAttribute("pagecount", pagecount);
        request.setAttribute("pageGroupCount", pageGroupCount);
        request.setAttribute("numPageGroup", numPageGroup);
        request.setAttribute("pageGroupSize", pageGroupSize);
        request.setAttribute("Grouplist", Grouplist);
        request.setAttribute("totalboardCount", totalboardCount);
        request.setAttribute("select_category", select_category);
        request.setAttribute("select_order", select_order);
        
        
        
        forward.setRedirect(false);
		forward.setPath("GroupBoardList.jsp");
		
		return forward;
	}

}