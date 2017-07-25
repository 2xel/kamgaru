/*
 * @ 프로젝트명 : 캠거루
 * @ 패키지명 : kr.kamgaru.utils;
 * @ 파일명 : MailInfo
 * @ 작성자 : 이재민
 * @ 작성일 : 2017.4.26
 * @ 설명 : 회원가입 인증메일 안에 들어갈 내용들을 생성
 */

package kr.kamgaru.utils;

public class MailInfo {
	private String email;
	private String joincode;
	private String content;
	
	public MailInfo() {}
	
	public MailInfo(String email, String joincode) {
		this.email = email;
		this.joincode = joincode;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getjoincode() {
		return joincode;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setRegisterContent() {
		this.content = 
				
				"<h1>캠거루 회원가입 하신것을 축하드립니다.</h1><br>"
				+ "이메일 주소를 인증하시려면 아래 버튼을 클릭해 주세요.<br>"
				+ "만약 본인이 회원가입을 하신것이 아니라면 인증하지 마시기 바랍니다.<br>"
				+ "아래의 링크를 클릭해 인증하세요.<br>"
				+ "<a href='http://localhost:8090/Kamgaru/register.min?email=" + email + "&joincode=" + joincode + "'>캠거루 인증하기</a><br>";
	}
	public void setfindpwdContent() {
		this.content = 
				
				"<h1>캠거루 비밀번호 재설정 방법입니다.</h1><br>"
				+ "비밀번호를 재설정하시려면 아래 버튼을 클릭해 주세요.<br>"
				+ "만약 본인이 아니라면 캠거루에 문의하여 주시기 바랍니다.<br>"
				+ "<a href='http://localhost:8090/Kamgaru/repwdpage.min?email=" + email + "&joincode=" + joincode + "'>비밀번호 재설정하러 가기</a><br>";
	}
}
