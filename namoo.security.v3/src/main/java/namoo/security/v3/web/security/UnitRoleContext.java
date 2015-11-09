package namoo.security.v3.web.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;

import namoo.security.v3.domain.authentication.entity.PMSRole;
import namoo.security.v3.z_library.StringUtil;
import namoo.security.v3.z_library.VizendException;

/**
 * 사용자 요청서비스에 대한 대상 과 사용자 역할정보 컨텍스트
 * 
 * @author kyyoon
 * @since 2011. 5. 16.
 */
public class UnitRoleContext {

    /** 로그인하지 않은 사용자 */
    public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";
    public static final List<String> ROLE_ANONYMOUS_LIST = new ArrayList<String>();

    public static final GrantedAuthority[] DEFAULT_GRANTED_AUTHORITY = new GrantedAuthority[] { new GrantedAuthorityImpl(ROLE_ANONYMOUS) };

    static {
        ROLE_ANONYMOUS_LIST.add(ROLE_ANONYMOUS);
    }

    private static ThreadLocal<List<PMSRole>> roles = new ThreadLocal<List<PMSRole>>();

    public static List<PMSRole> getRoles() {
        List<PMSRole> result = roles.get();
        if (result == null) {
            return Collections.emptyList();
        }
        return result;
    }

    public static void setRoles(List<PMSRole> userRoles) {
        roles.set(userRoles);
    }

    /**
     * 사용자의 역할목록을 제공한다.
     * 
     * @return 사용자에게 인가된 역할 목록
     */
    public static GrantedAuthority[] getAuthorities() {
        return makeAuthoritiesByRoles(roles.get());
    }

    /**
     * 역할정보로 권한을 생성하여 반환한다.
     * 
     * @param roles
     *            역할목록
     * @return 사용자에게 인가된 역할 목록
     */
    private static GrantedAuthority[] makeAuthoritiesByRoles(List<PMSRole> roles) {
        //
        if (roles == null || roles.isEmpty()) {
            return DEFAULT_GRANTED_AUTHORITY;
        }

        GrantedAuthority[] grantedAuthorities = new GrantedAuthority[roles.size()];
        for (int i = 0; i < roles.size(); i++) {
            grantedAuthorities[i] = new GrantedAuthorityImpl(roles.get(i).getId());
        }
        return grantedAuthorities;
    }
    
    /**
     * 역할아이디 목록을 반환한다.
     * 
     * @return 역할아이디 목록
     */
    public static List<String> getRoleIds() {
        //
        List<String> roleIds = new ArrayList<String>();
        for (PMSRole role : getRoles()) {
            roleIds.add(role.getId());
        }
        return roleIds;
    }

    public static boolean hasRole(String roleName) {
        for (String userRole : getRoleIds()) {
            if (userRole.equals(roleName))
                return true;
        }
        return false;
    }
    
    /**
     * 주역할 아이디를 반환한다. 
     * @return 주역할 아이디
     */
    public static String getMajorRoleId() {
        //
        List<PMSRole> roles = getRoles();
        if (roles.isEmpty()){
            // TODO 어떤 예외를 던저야 할까?
            throw new VizendException("역할목록이 없습니다.");
        } 
        
        String majorRoleId = roles.get(0).getId();
        if (StringUtil.isNullOrNullString(majorRoleId)) {
            throw new VizendException("주역할이 없습니다.");
        }
        return majorRoleId;
    }

    /**
     * 역할정보 컨텍스트를 비운다.
     */
    public static void clear() {
        roles.set(null);
    }

}
