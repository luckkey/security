package namoo.security.v3.z_library;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * HTTP Session과 관련한 유틸리티
 * 
 * @author 김동열
 * @since 2008. 11. 17
 */
public class HttpSessionUtil {
    public static final String BIZ_MSG = "BIZ_MSG";

    /**
     * HTTP Session에서 세션 키를 반환한다.
     * 
     * @param request
     *            HTTP Session
     * @return
     */
    public static String getSessionKey(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        if (httpSession != null) {
            return httpSession.getId();
        }
        return null;
    }

    public static HttpSession getSession(HttpServletRequest request) {
        return request.getSession();
    }

    /**
     * 업무 메시지 추출
     * 
     * @param request
     * @return
     */
    public static String getBizMsg(HttpServletRequest request) {
        Object value = getSession(request).getAttribute(BIZ_MSG);
        return value == null ? null : (String) value;
    }

    /**
     * 업무 메시지 설정
     * 
     * @param request
     * @param msg
     */
    public static void setBizMsg(HttpServletRequest request, String msg) {
        getSession(request).setAttribute(BIZ_MSG, msg);
    }
}
