package namoo.security.v3.service.authetication.da.mybatis.mapper;

import java.util.List;

import namoo.security.v3.domain.authentication.entity.PMSMenu;
import namoo.security.v3.z_library.Mapper;

/**
 * Authorization MyBatis Mapper Interface
 *
 * @author <a href="mailto:eykim@nextree.co.kr">Kim, Eunyoung</a>
 * @since 2015. 11. 4
 */
@Mapper
public interface MenuMapper {

    /**
     * 역할에 할당된 메뉴 및 화면 목록을 조회한다.
     * @param roleId 역할아이디
     * @return 메뉴 및 화면 목록
     */
    List<PMSMenu> selectMenuScreenListByRole(String roleId);

}
