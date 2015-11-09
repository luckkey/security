package namoo.security.v3.domain.authentication.entity;

import java.util.List;

import javax.servlet.ServletContext;

import namoo.security.v3.domain.authentication.service.AuthenticationService;
import namoo.security.v3.web.security.UnitRoleContext;
import namoo.security.v3.z_library.ComponentContext;

/**
 * 메뉴구성을 위한 Helper 클래스
 * : 현재 안쓰는 메소드는 삭제 
 * @author 김영진
 * @since 2009. 07. 08
 * 
 */
public class MenuBean {
    
	/**
	 * 현재 사용자의 역할에 해당하는 메뉴를 반환한다.
	 * 
	 * @param context
	 *            서블릿 Context
	 * @param projectId
	 *            프로젝트 아이디
	 * @return 현재 사용자의 메뉴
	 */
	public static PMSMenu getMenus(ServletContext servletContext) {
		//
		List<String> roleIds = UnitRoleContext.getRoleIds();
		
		AuthenticationService authenticationService = ComponentContext.lookup(servletContext, AuthenticationService.class);
		
		PMSMenu menu = authenticationService.inquireMenuByRoles(roleIds);
		
		return (menu == null) ? new PMSMenu() : menu;
	}

}
