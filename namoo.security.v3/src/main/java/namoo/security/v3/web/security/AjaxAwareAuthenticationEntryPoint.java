package namoo.security.v3.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.AuthenticationException;
import org.springframework.security.ui.webapp.AuthenticationProcessingFilterEntryPoint;

public class AjaxAwareAuthenticationEntryPoint extends AuthenticationProcessingFilterEntryPoint {
    public void commence(ServletRequest request, ServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    	if (AjaxAwareHelper.isAjax((HttpServletRequest) request)) {
    		((HttpServletResponse)response).sendError(401, "");
    	} else {
    		super.commence(request, response, authException);
    	}
    }
}
