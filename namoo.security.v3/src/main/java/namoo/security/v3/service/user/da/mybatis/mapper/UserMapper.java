package namoo.security.v3.service.user.da.mybatis.mapper;

import namoo.security.v3.domain.user.entity.AccountBDT;
import namoo.security.v3.domain.user.entity.UserBDT;
import namoo.security.v3.z_library.Mapper;
import namoo.security.v3.z_library.VizendException;

@Mapper
public interface UserMapper {

    /**
     * 사용자 정보 조회
     * 
     * @param userNo 사용자 번호
     * @return 사용자 계정
     */
    UserBDT selectUser(Long userNo);

    /**
     * 로그인아이디로 사용자 조회
     * 
     * @param loginId 로그인아이디
     * @return 사용자
     * @throws VizendException 로그인아이디가 없을 경우 발생
     */
    AccountBDT selectAccountByLoginId(String loginId);

}
