package namoo.security.v3.domain.authentication.store;

import java.util.List;

import namoo.security.v3.domain.authentication.entity.PMSRole;


/**
 * Role Store Interface
 * @author eykim
 * @since 2015. 11. 5.
 */
public interface RoleStore {
    
    /**
     * 역할 아이디로 역할정보를 조회한다.
     * 
     * @param id 역할 ID
     * @return 역할정보
     */
    PMSRole readRole(String id);
	
	/**
	 * 대상에 대한 사용자의 역할목록을 조회한다.
	 * 
	 * @param userNo 사용자아이디
	 * @param unit
	 * @return
	 */
	List<PMSRole> readRoleListByUserNo(Long userNo);

}
