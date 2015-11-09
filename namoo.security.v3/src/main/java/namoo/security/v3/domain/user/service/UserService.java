package namoo.security.v3.domain.user.service;

import namoo.security.v3.domain.user.entity.UserAccountBDT;

/**
 * 사용자 서비스
 * 
 * @author kyyoon
 * @since 2011. 8. 3.
 */
public interface UserService {
    
    /**
     * 로그인아이디로 사용자 조회
     * @param loginId 로그인아이디
     * @return 사용자
     * @throws VizendException 로그인아이디가 없을 경우 발생
     */
    UserAccountBDT readUserAccountByLoginId(String loginId);
}
