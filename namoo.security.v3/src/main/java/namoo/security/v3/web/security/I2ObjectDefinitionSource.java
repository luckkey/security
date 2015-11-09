package namoo.security.v3.web.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.Authentication;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.SecurityConfig;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.intercept.web.DefaultFilterInvocationDefinitionSource;
import org.springframework.security.intercept.web.FilterInvocation;
import org.springframework.security.util.AntUrlPathMatcher;
import org.springframework.security.util.UrlMatcher;

/**
 * 자원에 관한 접근 가능 여부를 판단한다.
 * 
 * @author Bee
 * @since 2009. 07. 07
 */
public class I2ObjectDefinitionSource extends DefaultFilterInvocationDefinitionSource {

	@SuppressWarnings("rawtypes")
	public I2ObjectDefinitionSource(UrlMatcher urlMatcher, LinkedHashMap requestMap) {
		super(urlMatcher, requestMap);
	}
	
	@SuppressWarnings("rawtypes")
	public I2ObjectDefinitionSource() {
		super(new AntUrlPathMatcher(), new LinkedHashMap());
	}

	private static Map<String, SecurityConfig> securityConfigs = new HashMap<String, SecurityConfig>();

    private SecurityConfig makeSecurityConfig(String roleId) {
        SecurityConfig config = securityConfigs.get(roleId);
        if (config == null) {
            config = new SecurityConfig(roleId);
            securityConfigs.put(roleId, config);
        }
        return config;
    }

    /**
     * 역할명으로 역할의 ConfigAttributeDefinition을 생성한다.
     * 
     * @param roles
     *            역할명
     * @return ACEGI에서 사용하는 ConfigAttributeDefinition
     */
    private ConfigAttributeDefinition makeConfigAttributeDefinition(List<String> permittedRoles) {
        List<SecurityConfig> configAttributes = new ArrayList<SecurityConfig>();
        for (String role : permittedRoles) {
        	configAttributes.add(makeSecurityConfig(role));
        }
        
        return new ConfigAttributeDefinition(configAttributes);
    }
    
    @Override
    public ConfigAttributeDefinition getAttributes(Object object) throws IllegalArgumentException {
        if ((object == null) || !this.supports(object.getClass())) {
            throw new IllegalArgumentException("Object must be a FilterInvocation");
        }
        
        return lookupAttributes((FilterInvocation) object);
    }

	public ConfigAttributeDefinition lookupAttributes(FilterInvocation invocation) {
		String url = invocation.getRequestUrl();
        // Strip anything after a question mark symbol, as per SEC-161. See also SEC-321
        int firstQuestionMarkIndex = url.indexOf("?");
        if (firstQuestionMarkIndex != -1) {
            url = url.substring(0, firstQuestionMarkIndex);
        }
        
        // acegi 설정에서 exclude한 페이지를 제외한 모든 리소스 접근은 로그인 한 사용자에게만 허락됨.
        if (!isAccessGrantedURLWithoutLogin(url)) {
            List<String> emptyList = Collections.emptyList();
            return makeConfigAttributeDefinition(emptyList);
        }
        
        // null을 리턴할 경우 인증 검사 대상에서 제외된다. 따라서 해당 URL은 무조건 인증에 통과하게 된다.
        return null;
    }
	
	/**
     * 해당 URL이 로그인 없이 접근 가능한 URL인지 판단한다.<br>
     * security필터로 들어오는 모든것은 로그인 되어야 한다.<br>
     * 로그인되지 않아도 접근 가능한 uri는 filter설정에서 처리한다.
     * 
     * @param url
     *            URL
     * @return 접근할 수 있으면 true, 없으면 false
     */
    private boolean isAccessGrantedURLWithoutLogin(String url) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof LoginUser)) {
            return false;
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.acegisecurity.intercept.ObjectDefinitionSource#getConfigAttributeDefinitions()
     */
    @SuppressWarnings("rawtypes")
	public Collection getConfigAttributeDefinitions() {
        return Collections.emptySet();
    }
    
}
