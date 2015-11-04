package namoo.security.exam.mybatis;

import namoo.security.exam.domain.User;

import org.apache.ibatis.annotations.Param;


public interface UserMapper {
	
	// 사용자 단건 조회
    User selectUserByUserId(@Param("userId") String userId);

}
