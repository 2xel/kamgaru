/*
 * @ 프로젝트명 : 캠거루
 * @ 패키지명 : kr.kamgaru.utils;
 * @ 파일명 : RandomJoinCode
 * @ 작성자 : 이재민
 * @ 작성일 : 2017.4.26
 * @ 설명 : 회원가입시 랜덤값으로 인증코드를 생성
 */

package kr.kamgaru.utils;

import java.util.Random;

public class RandomJoinCode {
	Random random = new Random();

	public static String getRandomJoinCode() {
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		int num = Math.abs(random.nextInt());
		return "kam" + num;
	}
}