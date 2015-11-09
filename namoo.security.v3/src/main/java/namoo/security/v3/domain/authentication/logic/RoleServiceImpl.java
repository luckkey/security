package namoo.security.v3.domain.authentication.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import namoo.security.v3.domain.authentication.entity.PMSRole;
import namoo.security.v3.domain.authentication.service.RoleService;
import namoo.security.v3.domain.authentication.store.RoleStore;

/**
 * 역할관리용 인터페이스 구현체이다., 시스템에서 사용되는 역할들을 정의하고 해당 역할을 수정, 삭제, 조회할 수 있는 기능을 제공한다.
 * 
 * @author Bee
 * @since 2009. 06. 29
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleStore roleStore;
	
    @Override
    public PMSRole inquireRole(String id) {
        return roleStore.readRole(id);
    }
	
    @Override
	public List<PMSRole> inquireUserRole(Long userNo) {
		return roleStore.readRoleListByUserNo(userNo);
	}



}
