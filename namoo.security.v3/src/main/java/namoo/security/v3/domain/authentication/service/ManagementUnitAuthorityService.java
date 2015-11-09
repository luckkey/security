package namoo.security.v3.domain.authentication.service;

import java.util.List;

import namoo.security.v3.domain.authentication.entity.PMSMenu;


/**
 * 관리대상에 대한 역할별 메뉴 접근 권한 관리 서비스
 *
 * @author <a href="mailto:kyyoon@nextree.co.kr">kyyoon</a>
 * @since 2012. 5. 24.
 */
public interface ManagementUnitAuthorityService {

    /**
     * 대상역할이 접근가능한 시스템의 메뉴목록을 조회한다.
     * @param roleId 역할 아이디
     * @return 역할에 설정된 메뉴 목록
     */
    List<PMSMenu> readRoleMenuList(String roleId);
    
	/**
	 * 대상역할에 설정된 관리대상의 메뉴 목록을 조회한다.<br>
	 * 
	 * @param muType 대상 유형 {@link ManagementUnitType}
	 * @param muId 대상 아이디
	 * @param roleId 역할 아이디
	 * @return 대상역할에 설정된 관리대상 메뉴 목록.
	 */
//	List<AdaptedMenu> readRoleMenuList(String muType, Long muId, String roleId);

	/**
	 * 대상역할에 선택된 메뉴의 접근 권한을 추가한다.<br>
	 * 하위 메뉴 포함.
	 * 
	 * @param muType 대상 유형 {@link ManagementUnitType}
	 * @param muId 대상 아이디
	 * @param roleId 역할 아이디
	 * @param resourceId 메뉴 아이디
	 * @param urn 메뉴 URN
	 */
//	void assignMenuToRole(String muType, Long muId, String roleId, Long resourceId, String urn);

	/**
	 * 대상역할에서 선택된 메뉴의 접근 권한을 삭제한다.<br>
	 * 하위 메뉴 포함.
	 * 
	 * @param muType 대상 유형 {@link ManagementUnitType}
	 * @param muId 대상 아이디
	 * @param roleId 역할 아이디
	 * @param resourceId 메뉴 아이디
	 * @param urn 메뉴 URN
	 */
//	void unassignMenuFromRole(String muType, Long muId, String roleId, Long resourceId, String urn);

	/**
	 * 대상역할에서 선택된 메뉴의 권한을 변경한다.<br>
	 * 
	 * @param muType 대상 유형 {@link ManagementUnitType}
	 * @param muId 대상 아이디
	 * @param authority 변경된 권한 정보 {roleId, ManagementMenu(resourceId, urn)  정보 포함.}
	 */
//	void editMenuAuthority(String muType, Long muId, ManagementAuthority authority);


}
