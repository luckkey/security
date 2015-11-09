package namoo.security.v3.web.security;

import javax.servlet.http.HttpServletRequest;

public class AjaxAwareHelper {

	private final static String APPLICATION_JSON = "application/json";

    public static boolean isAjax(HttpServletRequest request) {
    	// 요청 해더에서 Accept 가 application/json 을 포함한다면 json 요청으로 인식한다.
    	String acceptHeader = ((HttpServletRequest)request).getHeader("Accept");
    	if (acceptHeader != null) {
    		if(acceptHeader.indexOf(APPLICATION_JSON) >= 0) {
    			return true;
    		}
    	}

    	// URI에 fragment를 포함하면 Ajax요청으로 인식한다.(ajax로 페이지를 요청하는 경우임)
    	String requestURI = request.getRequestURI();
    	if (requestURI != null) {
    		if (requestURI.contains("/fragment")) return true;
    	}

    	return false;
    }
}
