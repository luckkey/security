package namoo.security.v3.web.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.providers.AbstractAuthenticationToken;

/**
 * 로그인아이디와 비밀번호로 인증하기 위한 인증토큰(PMS용 ACEGI 연동용).
 * <p>
 * 이 인증을 위해 필요한 정보
 * <ul>
 * <li>로그인아이디</li>
 * <li>비밀번호</li>
 * <li>접속아이피</li>
 * </ul>
 * </p>
 * 
 * @author Bee
 * @since 2009. 07. 07
 */
public class I2FormAuthenticationToken extends AbstractAuthenticationToken {

     private static final long serialVersionUID = 1604534176179107931L;
     
     /** 로그인아이디 */
     private Object principal;
     /** 비밀번호 */
     private Object credentials;
     /** 접속아이피 */
     private String accessIp;
     /** HTTP Request */
     private HttpServletRequest request;     

    /**
     * @param principal 로그인 아이디
     * @param credentials 비밀번호
     * @param accessIp 접속아이피
     * @param request HTTP 요청 객체
     */
    public I2FormAuthenticationToken(Object principal, Object credentials, String accessIp, HttpServletRequest request) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.accessIp = accessIp;
        this.request = request;   
        setAuthenticated(false);
    }

    /* (non-Javadoc)
     * @see org.acegisecurity.Authentication#getCredentials()
     */
    public Object getCredentials() {
        return this.credentials;
    }

    /* (non-Javadoc)
     * @see org.acegisecurity.Authentication#getPrincipal()
     */
    public Object getPrincipal() {
        return this.principal;
    }

    /**
     * @return accessIp
     */
    public String getAccessIp() {
        return this.accessIp;
    }

    /**
     * @return request
     */
    public HttpServletRequest getRequest() {
        return this.request;
    }
    
    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor containing GrantedAuthority[]s instead");
        }
        super.setAuthenticated(false);
    }    

}
