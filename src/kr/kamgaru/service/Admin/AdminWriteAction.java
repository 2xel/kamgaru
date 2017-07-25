package kr.kamgaru.service.Admin;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;
import kr.kamgaru.DTO.AdminDTO;

public class AdminWriteAction implements Action{

   @Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=UTF-8");

		DAO dao = new DAO();
		AdminDTO admDto = new AdminDTO();
		String saveDir = request.getServletContext().getRealPath("/upload");
		int maxSize = 1024*1024*100;
		String encType = "UTF-8";

		MultipartRequest multi
		= new MultipartRequest(request, saveDir, maxSize, encType, new DefaultFileRenamePolicy());
		
	    File file = multi.getFile("attach");
	    
	    admDto.setManager_name(multi.getParameter("manager_name"));
		admDto.setManager_phone(multi.getParameter("manager_phone"));
		admDto.setFieldCode(multi.getParameter("fieldCode"));
		admDto.setCategoryCode(multi.getParameter("category"));
		admDto.setTitle(multi.getParameter("title"));
		admDto.setContents(multi.getParameter("contents"));
		admDto.setStartDate(multi.getParameter("start_date"));
		admDto.setEndDate(multi.getParameter("end_date"));
		admDto.setWebsite(multi.getParameter("website"));
		admDto.setImage(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
		admDto.setPrizeMoney(multi.getParameter("prizemoney"));
		admDto.setPlace(multi.getParameter("place"));

		// 테스트해보고 주석처리하세요
		System.out.println("**TEST > multi.getAttach : " + saveDir + multi.getFilesystemName((String)multi.getFileNames().nextElement()));
		System.out.println("**TEST > multi.getTitle : " + multi.getParameter("title"));

		int result = dao.writeRequest(admDto);

		System.out.println("Action result: " + result);

		if (result > 0) {
			ActionForward forward = new ActionForward();
			System.out.println("글작성완료");
			forward.setRedirect(true);
			forward.setPath(request.getContextPath()+"/index.jsp");
			return forward;

		} else {
			System.out.println("글 입력 실패");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 입력을 실패했습니다');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		return null;
   }
}