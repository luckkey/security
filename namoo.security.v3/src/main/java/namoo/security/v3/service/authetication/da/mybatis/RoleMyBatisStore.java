package namoo.security.v3.service.authetication.da.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import namoo.security.v3.domain.authentication.entity.PMSRole;
import namoo.security.v3.domain.authentication.store.RoleStore;
import namoo.security.v3.service.authetication.da.mybatis.mapper.RoleMapper;

/**
 * Role Store MyBatis Implementor
 * - UserRoleUtilImpl, UserRoleHelper 참고
 * 
 * @author eykim
 * @since 2015. 11. 5.
 */
@Repository
public class RoleMyBatisStore implements RoleStore {
    

    @Autowired
    private RoleMapper roleMapper;
    
    @Override
    public PMSRole readRole(String id) {
        //
        return roleMapper.selectRole(id);
    }
    
    @Override
    public List<PMSRole> readRoleListByUserNo(Long userNo) {
        //
        // TODO 비즈니스 분석 필요 
        return roleMapper.selectRoleListByUserNo(userNo);
    }

}
