/*
 * @ 프로젝트명 : 캠거루
 * @ 패키지명 : kr.kamgaru.Controller;
 * @ 파일명 : KamgaruMemberController
 * @ 작성자 : 이재민
 * @ 작성일 : 2017.4.25
 * @ 설명 : 회원관련 페이지 Controller
 */
package kr.kamgaru.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.service.Member.MemberEmailJoinAction;
import kr.kamgaru.service.Member.MemberFindpwdAction;
import kr.kamgaru.service.Member.MemberJoinAction;
import kr.kamgaru.service.Member.MemberLoginAction;
import kr.kamgaru.service.Member.MemberMypageAction;
import kr.kamgaru.service.Member.MemberRepwdAction;
import kr.kamgaru.service.Member.MemberRepwdOkAction;


@WebServlet("*.min")
public class KamgaruMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public KamgaruMemberController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {	
		doProcess(request, response);
		
	}

	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String cmdURI = requestURI.substring(contextPath.length());

		ActionForward forward = null;
		Action action = null;
		System.out.println("들어온주소 : " + cmdURI);
		
		//1. 로그인
		if (cmdURI.equals("/Login/login.min")) {
			try {
				action = new MemberLoginAction();
				System.out.println("로그인 시도중");
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		//2. 이메일인증을 통해 joincode 활성화
		}else if (cmdURI.equals("/register.min")) {
			try {
				System.out.println("이메일인증요청");
				action = new MemberEmailJoinAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		//3. 회원가입
		}else if (cmdURI.equals("/Login/Joinok.min")) {
			try {
				System.out.println("회원가입요청");
				action = new MemberJoinAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		//4. 비밀번호 찾기
		}else if (cmdURI.equals("/Login/findpwd.min")) {
			try {
				System.out.println("비밀번호 찾기 요청");
				action = new MemberFindpwdAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		//5. 비밀번호 찾기 후 비밀번호 재설정 페이지 요청
		}else if (cmdURI.equals("/repwdpage.min")) {
			try {
				System.out.println("이메일에서 비밀번호 재설정 페이지 요청");
				action = new MemberRepwdAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		//6. 비밀번호 재설정 완료
		}else if (cmdURI.equals("/Login/repwdok.min")) {
			try {
				System.out.println("이메일에서 비밀번호 재설정 완료");
				action = new MemberRepwdOkAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//7. 비밀번호 재설정 완료
		}else if (cmdURI.equals("/Login/Mypage.min")) {
			try {
				System.out.println("회원정보 수정 요청");
				action = new MemberMypageAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
	}
}
