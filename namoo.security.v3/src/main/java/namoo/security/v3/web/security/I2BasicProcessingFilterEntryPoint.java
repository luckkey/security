package namoo.security.v3.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.AuthenticationException;
import org.springframework.security.ui.AuthenticationEntryPoint;

/**
 * Basic Authentication 의 EntryPoint 에서 realmName이 지정되지 않을때 
 * No WWW-Authenticate Header is present. 를 추가
 * @author 염성국
 *
 */
public class I2BasicProcessingFilterEntryPoint implements AuthenticationEntryPoint, InitializingBean {
    
	private String realmName;

    public String getRealmName() {
        return realmName;
    }
	
	public void afterPropertiesSet() throws Exception {
		//Assert.hasText(realmName, "realmName must be specified");
    }

    public void commence(ServletRequest request, ServletResponse response, AuthenticationException authException)
        throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if(realmName != null){
        	httpResponse.addHeader("WWW-Authenticate", "Basic realm=\"" + realmName + "\"");
        }else{
        	httpResponse.addHeader("WWW-Authenticate", "No WWW-Authenticate Header is present.");
        }
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }

}
