/*
 * @ 프로젝트명 : 캠거루
 * @ 패키지명 : kr.kamgaru.ajax;
 * @ 파일명 : ContestIndexList
 * @ 작성자 : 이재민(jaeminstar@naver.com)
 * @ 작성일 : 2017.4.28
 * @ 설명 : aJax로 Index 화면에 공모전 리스트 주는 Servlet
 */

package kr.kamgaru.ajax;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.kamgaru.DAO.IndexListDAO;
import kr.kamgaru.DTO.IndexDTO;

@WebServlet(description = "공모전 Index list 출력", urlPatterns = { "/ContestIndex" })
public class ContestIndexList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ContestIndexList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		JSONArray jsonarray = new JSONArray();

		try {		
			IndexListDAO contestindexlistdao = new IndexListDAO();
			ArrayList<IndexDTO> dtoArr = contestindexlistdao.ContestIndexList();
			IndexDTO indexdto = new IndexDTO();
			
			for(int i=0; i<dtoArr.size(); i++){
				indexdto = dtoArr.get(i);
				JSONObject jsonobject = new JSONObject();
				
				jsonobject.put("id", indexdto.getBoardid());
				jsonobject.put("endDate", indexdto.getEnddate());
				jsonobject.put("viewCount", indexdto.getHit());
				jsonobject.put("title", indexdto.getTitle());
				jsonobject.put("company", indexdto.getPlace());



				jsonarray.add(jsonobject);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		response.getWriter().print("{\""+"contests"+"\":" + jsonarray + "}");
	}
}