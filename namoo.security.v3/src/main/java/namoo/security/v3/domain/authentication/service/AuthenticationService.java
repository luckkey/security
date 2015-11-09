package namoo.security.v3.domain.authentication.service;

import java.util.List;

import namoo.security.v3.domain.authentication.entity.PMSMenu;
import namoo.security.v3.z_library.security.AuthResult;
import namoo.security.v3.z_library.security.SessionInfo;

/**
 * 
 * 사용자 인증 처리을 유틸리티 컴포넌트의 인터페이스이다.<br>
 * 인증 처리를 위한 사용자 ID 및 비밀번호 확인을 위한 인터페이스 및 시스템 접근 로그, 로그아웃 처리와 같은 기능을 제공한다.
 * 
 * @author Bee
 * @since 2009. 06. 29
 */
public interface AuthenticationService {

	/**
	 * 사용자 로그인 아이디와 비밀번호로 사용자를 인증처리한다.
	 * 
	 * @param loginId
	 *            사용자 로그인 아이디
	 * @param password
	 *            사용자 로그인 패시워드
	 * @param session
	 *            사용자 세션정보
	 * @return 인증처리결과
	 */
	AuthResult authenticate(String loginId, String password, SessionInfo session);

    /**
     * 역할아이디목록에 해당하는 메뉴목록을 정렬하여 최상위 메뉴에 하위 메뉴를 셋팅하여 반환한다.
     * @param roleIds 역할 아이디 목록
     * @return 메뉴 정보(Not Null을 보장한다.)
     */
    PMSMenu inquireMenuByRoles(List<String> roleIds);

}
