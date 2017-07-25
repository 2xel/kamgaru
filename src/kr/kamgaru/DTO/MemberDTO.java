package kr.kamgaru.DTO;

public class MemberDTO {
	private String university;
	private String email;
	private String pwd;
	private String name;
	private String nickname;
	private String joincode;
	private Boolean joincheck;
	private String memcode;
	
	public MemberDTO(){}
	
	//이메일 인증시 쓰이는 DTO
	public MemberDTO(String email, String joincode){
		this.email = email;
		this.joincode = joincode;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getJoincode() {
		return joincode;
	}

	public void setJoincode(String joincode) {
		this.joincode = joincode;
	}

	public Boolean getJoincheck() {
		return joincheck;
	}

	public void setJoincheck(Boolean joincheck) {
		this.joincheck = joincheck;
	}

	public String getMemcode() {
		return memcode;
	}

	public void setMemcode(String memcode) {
		this.memcode = memcode;
	}
	


}
