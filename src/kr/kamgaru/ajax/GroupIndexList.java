/*
 * @ 프로젝트명 : 캠거루
 * @ 패키지명 : kr.kamgaru.ajax;
 * @ 파일명 : GroupIndexList
 * @ 작성자 : 이재민(jaeminstar@naver.com)
 * @ 작성일 : 2017.4.26
 * @ 설명 : aJax로 Index 화면에 동아리 리스트 주는 Servlet
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

@WebServlet(description = "동아리 Index list 출력", urlPatterns = { "/GroupIndex" })
public class GroupIndexList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GroupIndexList() {
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
			IndexListDAO indexlistdao = new IndexListDAO();
			ArrayList<IndexDTO> dtoArr = indexlistdao.Groupindexlist();
			IndexDTO groupindexdto = new IndexDTO();
			
			for(int i=0; i<dtoArr.size(); i++){
				groupindexdto = dtoArr.get(i);
				JSONObject jsonobject = new JSONObject();

				jsonobject.put("id", groupindexdto.getBoardid());
				jsonobject.put("title", groupindexdto.getTitle());
				jsonobject.put("contents", groupindexdto.getContents());
				jsonobject.put("endDate", groupindexdto.getEnddate());
				jsonobject.put("viewCount", groupindexdto.getHit());
				jsonobject.put("clubImage", groupindexdto.getImage());
				jsonobject.put("clubName", groupindexdto.getGroupname());
				jsonarray.add(jsonobject);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		response.getWriter().print("{\""+"recruits"+"\":" + jsonarray + "}");
	}
}















