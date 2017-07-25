/*
 * @ 프로젝트명 : 캠거루
 * @ 패키지명 : kr.kamgaru.ajax;
 * @ 파일명 : communityIndexList
 * @ 작성자 : 이재민(jaeminstar@naver.com)
 * @ 작성일 : 2017.4.26
 * @ 설명 : aJax로 Index 화면에 커뮤니티 리스트 주는 Servlet
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

@WebServlet(description = "커뮤니티 Index list 출력", urlPatterns = { "/CommunityIndex" })
public class CommunityIndexList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommunityIndexList() {
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
			IndexListDAO communityindexlistdao = new IndexListDAO();
			ArrayList<IndexDTO> dtoArr = communityindexlistdao.CommunityIndexList();
			IndexDTO indexdto = new IndexDTO();
			
			for(int i=0; i<dtoArr.size(); i++){
				indexdto = dtoArr.get(i);
				JSONObject jsonobject = new JSONObject();
				
				jsonobject.put("id", indexdto.getComcode());
				jsonobject.put("name", indexdto.getComtitle());
				jsonobject.put("info", indexdto.getComcontents());
				jsonobject.put("picture", "https://cf-ccp.campuspick.com/149007811654752.jpg");
				jsonobject.put("isPicked", false);
				jsonobject.put("hasNewArticle", false);
				jsonobject.put("localId", "0");
				jsonobject.put("category", "20");
				jsonobject.put("categoryName", "");
				jsonobject.put("pickCount", "");
				jsonobject.put("localId", "0");
				jsonarray.add(jsonobject);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		response.getWriter().print("{\""+"communities"+"\":" + jsonarray + "}");
	}
}