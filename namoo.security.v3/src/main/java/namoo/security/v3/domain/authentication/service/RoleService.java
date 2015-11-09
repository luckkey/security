package namoo.security.v3.domain.authentication.service;

import java.util.List;

import namoo.security.v3.domain.authentication.entity.PMSRole;

public interface RoleService {
    
    /**
     * 역할 아이디로 역할정보를 조회한다.
     * 
     * @param id
     *            역할 ID
     * @return 역할정보
     */
    PMSRole inquireRole(String id);

	/**
	 * 사용자의 역할 목록을 조회한다.
	 * 
	 * @param userNo 사용자 번호
	 * @return 사용자의 역할 목록
	 */
	List<PMSRole> inquireUserRole(Long userNo);

    
}
