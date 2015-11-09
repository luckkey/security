package namoo.security.v3.web.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.Authentication;
import org.springframework.security.providers.AbstractAuthenticationToken;
import org.springframework.security.ui.AbstractProcessingFilter;
import org.springframework.security.ui.digestauth.NonceExpiredException;

import namoo.security.v3.z_library.I18NUtil;
import namoo.security.v3.z_library.RSACrypto;
import namoo.security.v3.z_library.RSAkeyPair;
import namoo.security.v3.z_library.RSAkeyPairContextHolder;
import namoo.security.v3.z_library.SecurityMessageFormat;

public class I2AuthenticationProcessingFilter extends AbstractProcessingFilter{
	
	/**
     * 로그인아이디 파라메터 이름
     */
    public static final String ACEGI_SECURITY_FORM_USERNAME_KEY = "j_username";
    /**
     * 로그인 비밀번호 파라메터 이름
     */
    public static final String ACEGI_SECURITY_FORM_PASSWORD_KEY = "j_password";

    private String credentialsCharset = "UTF-8";
    
    private String encodedAuthenticationParameter = "_p";
	
	private String encodedAuth;
	
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request) {
        
    	encodedAuth = request.getParameter(encodedAuthenticationParameter);
    	AbstractAuthenticationToken authRequest = null;
    	if(encodedAuth != null){
			try {
				authRequest = makeAuthenticationBase64Token(request);
			} catch (UnsupportedEncodingException e) {
				encodedAuth = null;
			}
    	}
    	
    	if(authRequest == null){
    		authRequest = makeAuthenticationToken(request);
    	}
    	
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    public String getDefaultFilterProcessesUrl() {
        return "/j_acegi_security_check";
    }

    private AbstractAuthenticationToken makeAuthenticationToken(HttpServletRequest request) {
        //로그인아이디, 비밀번호, 접속아이피를 획득한다.
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        String accessIp = request.getRemoteAddr();

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }
        username = username.trim();
        
        // 인증정보 토큰을 생성한다.
        AbstractAuthenticationToken authRequest = new I2FormAuthenticationToken(username, password, accessIp, request);

        return authRequest;
    }
    
    private AbstractAuthenticationToken makeAuthenticationBase64Token(HttpServletRequest request)
			throws UnsupportedEncodingException {
		byte[] base64Token = encodedAuth.getBytes(credentialsCharset);
		String token = new String(Base64.decodeBase64(base64Token), credentialsCharset);
		
		int delim = token.indexOf(":");

		if (delim != -1) {
		    String username = token.substring(0, delim);
		    String password = token.substring(delim + 1);
		    
		    return new I2FormAuthenticationToken(username, password, request.getRemoteAddr(), request);
		}
		return null;
	}
    
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(ACEGI_SECURITY_FORM_USERNAME_KEY);
    }
    
    protected String obtainPassword(HttpServletRequest request) {
    	String password = request.getParameter(ACEGI_SECURITY_FORM_PASSWORD_KEY);
    	if(SecurityMessageFormat.getSecurityMessageFormat().equals(SecurityMessageFormat.FORMAT_RSA)){
        	//password 복호화
        	//1. 세션으로 부터 개인키를 가져온다.
        	RSAkeyPair keyPair = RSAkeyPairContextHolder.getContext();
        	
        	//1.1 세션에 개인키가 존재하지 않을경우 개인키 발급실패 Exeception
        	if(keyPair == null){
        		throw new NonceExpiredException("개인키가 발급되지 않았습니다.");
        	}
        	//1.2 한번 사용된 키는 다시 사용할 수 없습니다.
        	if(keyPair.isUsed()){
        		throw new NonceExpiredException("이미 사용된 키입니다.");
        	}
        	//2. 복호화 처리
        	try{
        		password = RSACrypto.decrypt(password, keyPair.getPrivateKey());
        	}catch(Exception e){
        		throw new NonceExpiredException("암복호화 오류 발생");
        	}
        	//3. 세션의 개인키를 삭제한다.(개인키는 사용할 경우 재사용을 금지한다.)
        	keyPair.setUsed(true);
        }else if(SecurityMessageFormat.getSecurityMessageFormat().equals(SecurityMessageFormat.FORMAT_BASE64)){
        	byte[] bytes;
			try {
				bytes = password.getBytes(credentialsCharset);
				password = new String(Base64.decodeBase64(bytes), credentialsCharset);
			} catch (UnsupportedEncodingException e) {
				password = null;
			}
        }
        
		return password;
    }
    
	protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
		LoginUser loginUser = LoginUser.getLoginUser();
		LoginUser.setLoginUserLocale(I18NUtil.getUserLocale(request, loginUser.getLanguage()));
		LoginUser.setLoginUserTimeZone(I18NUtil.getUserTimeZone(loginUser.getTimeZoneId()));
		getRememberMeServices().loginSuccess(request, response, authResult);
	}

	public int getOrder() {
		return 0;
	}

}
