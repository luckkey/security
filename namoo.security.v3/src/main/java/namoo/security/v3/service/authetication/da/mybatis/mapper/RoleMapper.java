package namoo.security.v3.service.authetication.da.mybatis.mapper;

import java.util.List;

import namoo.security.v3.domain.authentication.entity.PMSRole;
import namoo.security.v3.z_library.Mapper;

@Mapper
public interface RoleMapper {

    /**
     * 역할아이디(PK)로 역할 정보를 조회한다.
     * @param id 역할아이디
     * @return 역할 정보
     */
    PMSRole selectRole(String id);
    
    /**
     * 사용자 역할 목록을 조회한다.
     * @param userNo 사용자 아이디
     * @return 사용자 역할 목록
     */
    List<PMSRole> selectRoleListByUserNo(Long userNo);

}
