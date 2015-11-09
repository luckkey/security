package namoo.security.v3.domain.authentication.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import namoo.security.v3.domain.authentication.entity.PMSMenu;
import namoo.security.v3.domain.authentication.service.ManagementUnitAuthorityService;
import namoo.security.v3.domain.authentication.store.MenuStore;

@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class ManagementUnitAuthorityServiceImpl implements ManagementUnitAuthorityService {

    @Autowired
    private MenuStore store;
    
    @Override
    public List<PMSMenu> readRoleMenuList(String roleId) {
        List<PMSMenu> systemMenus = store.readMenuListByRole(roleId);
        return systemMenus;
    }

}
