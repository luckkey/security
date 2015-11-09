package namoo.security.v3.web.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.AccessDeniedException;
import org.springframework.security.ui.AccessDeniedHandlerImpl;

public class AjaxAwareAccessDeniedHandler extends AccessDeniedHandlerImpl {

	private String errorPage;
	
	@Override
	public void handle(
			ServletRequest request,
			ServletResponse response,
			AccessDeniedException accessDeniedException
	) throws IOException, ServletException {
    	if (AjaxAwareHelper.isAjax((HttpServletRequest) request)) {
    		((HttpServletResponse)response).sendError(403, "");
    	} else {
//    		super.handle(request, response, accessDeniedException);
            if (errorPage != null) {
                // Put exception into request scope (perhaps of use to a view)
                ((HttpServletRequest) request).setAttribute(SPRING_SECURITY_ACCESS_DENIED_EXCEPTION_KEY,
                    accessDeniedException);

                // Perform RequestDispatcher "forward"
                RequestDispatcher rd = request.getRequestDispatcher(errorPage);
                rd.forward(request, response);
            } else {
            	((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
            }
    	}
	}
	
    public void setErrorPage(String errorPage) {
        if ((errorPage != null) && !errorPage.startsWith("/")) {
            throw new IllegalArgumentException("errorPage must begin with '/'");
        }

        this.errorPage = errorPage;
    }

}
