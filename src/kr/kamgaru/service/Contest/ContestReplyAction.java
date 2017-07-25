package kr.kamgaru.service.Contest;

import java.io.File;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.DAO.DAO;
import kr.kamgaru.DTO.AdminDTO;

public class ContestReplyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("Action - new DAO()");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=UTF-8");

		DAO dao= new DAO();
		
		String boardid = request.getParameter("BoardId");
		int MemCode = Integer.parseInt(request.getParameter("MemCode"));
		String contents = request.getParameter("Contents");
		contents = URLDecoder.decode(contents,"UTF-8");
		
		int result = dao.contestReplyWrite( boardid,MemCode, contents);
		System.out.println("Contestdsdas:"+boardid);

		//서비스 객체 호출하고
		//insert 하고
		//int result = dao.replywrite(Integer.parseInt(idx), writer, userid, content, pwd);
		
		if(result>0) {
			request.setAttribute("result", "success");
		}
		else {
			request.setAttribute("result", "fail");
		}
		
		//request.setAttribute("idx", boardid);
		
		ActionForward forward = null;
	  
		return forward;
	}

}
