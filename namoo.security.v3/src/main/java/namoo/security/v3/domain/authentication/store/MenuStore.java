package namoo.security.v3.domain.authentication.store;

import java.util.List;

import namoo.security.v3.domain.authentication.entity.PMSMenu;

public interface MenuStore {
    
    /**
     * 역할에 할당된 메뉴 목록을 조회한다.
     * 
     * @param roleId 역할 아이디
     * @return 순서대로 정렬된 메뉴목록
     */
    List<PMSMenu> readMenuListByRole(String roleId);
}
