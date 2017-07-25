/*
 * @ 프로젝트명 : 캠거루
 * @ 패키지명 : kr.kamgaru.ajax;
 * @ 파일명 : EmailCheck
 * @ 작성자 : 이재민
 * @ 작성일 : 2017.4.25
 * @ 설명 : 회원가입 페이지에 닉네임을 aJax로 DB와 연동 중복확인
 */

package kr.kamgaru.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.DAO.MemberDAO;
import kr.kamgaru.DTO.MemberDTO;

@WebServlet(description = "닉네임 중복검사", urlPatterns = { "*.nickname" })
public class NicknameCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NicknameCheck() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String nickname = request.getParameter("nickname");

		try {		
			MemberDAO Memberdao = new MemberDAO();
			MemberDTO Memberdto = new MemberDTO();
			
			Memberdto.setNickname(nickname);
			
			int result=Memberdao.nicknameduplicationcheck(Memberdto.getNickname());
			
			if(result!=0){
				out.print(false);
			}else{
				out.print(true);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
