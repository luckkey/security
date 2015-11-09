package namoo.security.v3.domain.user.store;

import namoo.security.v3.domain.user.entity.UserAccountBDT;
import namoo.security.v3.domain.user.entity.UserBDT;
import namoo.security.v3.z_library.VizendException;

/**
 * 사용자 Provider
 */
public interface UserStore {
    
    /**
     * 사용자 정보 조회
     * 
     * @param userNo 사용자 번호
     * @return 사용자 계정
     */
    UserBDT readUser(Long userNo);

    /**
     * 로그인아이디로 사용자 조회
     * 
     * @param loginId
     *            로그인아이디
     * @return 사용자
     * @throws VizendException
     *             로그인아이디가 없을 경우 발생
     */
    UserAccountBDT readUserAccountByLoginId(String loginId);
}
