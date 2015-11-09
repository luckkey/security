package namoo.security.v3.web.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.ui.SpringSecurityFilter;

import namoo.security.v3.domain.authentication.entity.PMSRole;
import namoo.security.v3.domain.authentication.service.RoleService;

/**
 * 인증처리가 완료 된 후 요청 URL을 통해 사용자의 역할을 페이지 구성 범위내에서 사용할 수 있도록 설정한다.<br>
 * 페이지 구성이 모두 완료되면 정보를 삭제한다.<br>
 * FIXME I2에서는 대상(PG, PD...)을 제외하기 때문에 해당내용을 변경 - 참고 
 * 본 필터는 sitemesh, jersey filter 앞단에 위치시켜야 한다.
 * 
 * @author kyyoon
 * @since 2011. 8. 31.
 */
public class I2RoleFilter extends SpringSecurityFilter{
    
    @Autowired
	private RoleService roleService;

    public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
    
	@Override
	protected void doFilterHttp(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 로그인 한 사용자 역할정보를 설정한다.
	    List<PMSRole> roles = roleService.inquireUserRole(LoginUser.getLoginUserNo());
        UnitRoleContext.setRoles(roles);
        chain.doFilter(request, response);
        
        UnitRoleContext.clear();
	}

	public int getOrder() {
		return 0;
	}
}
