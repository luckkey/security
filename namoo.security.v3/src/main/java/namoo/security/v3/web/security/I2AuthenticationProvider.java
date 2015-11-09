package namoo.security.v3.web.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationCredentialsNotFoundException;
import org.springframework.security.AuthenticationServiceException;
import org.springframework.security.BadCredentialsException;
import org.springframework.security.DisabledException;
import org.springframework.security.InsufficientAuthenticationException;
import org.springframework.security.providers.AuthenticationProvider;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.util.Assert;

import namoo.security.v3.domain.authentication.service.AuthenticationService;
import namoo.security.v3.domain.user.entity.UserAccountBDT;
import namoo.security.v3.z_library.HttpSessionUtil;
import namoo.security.v3.z_library.security.AuthResult;
import namoo.security.v3.z_library.security.SessionInfo;

/**
 * Spring Security 인증서비스 제공자
 *
 * @author 박기영
 * @since 2010. 1. 5.
 */

public class I2AuthenticationProvider implements AuthenticationProvider {
    /** 이 Provider가 인증할 수 있는 인증토큰(kr.nextree.vizend.security.I2FormAuthenticationToken) */

    @SuppressWarnings("rawtypes")
    private static final Class VALIDE_TOKEN_CLASS = I2FormAuthenticationToken.class;

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * 인증 작업을 수행한다. 인증 객체에 대한 정합성을 체크한 후 실제 인증 절차를 진행한다. 인증이 성공적으로 완료될 경우 인증 토근 객체를 생성하여 반환한다.
     * 
     * @param authentication
     *            인증 객체
     * @return 인증 토근 객체
     */
    public Authentication authenticate(Authentication authentication) {
        // 라이센스 적용(지우지 말것. 2010-04-13 최영목)
        // Logger.log();

        verifyAuthenticationInfo(authentication);

        I2FormAuthenticationToken authToken = (I2FormAuthenticationToken) authentication;

        String loginId = authToken.getName();
        String password = authToken.getCredentials().toString();
        HttpServletRequest request = authToken.getRequest();

        String accessIp = request.getRemoteAddr();
        String sessionKey = HttpSessionUtil.getSessionKey(request);

        // 인증수행
        AuthResult authResult = authenticationService.authenticate(loginId, password,
                new SessionInfo(sessionKey, accessIp));

        Authentication result = processAuthResult(authentication, authResult, authToken.getAccessIp());

        return result;

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public boolean supports(Class authentication) {
        return VALIDE_TOKEN_CLASS.isAssignableFrom(authentication);
    }

    /**
     * 인증 객체의 정합성을 체크한다.
     * 
     * @param authentication
     *            인증 객체
     */
    private void verifyAuthenticationInfo(Authentication authentication) {

        Assert.isInstanceOf(VALIDE_TOKEN_CLASS, authentication,
                "Only " + VALIDE_TOKEN_CLASS.getName() + " is supported");

        // 인증토큰으로 변경 후 로그인구분(loginKind), 로그인아이디(loginId), 비밀번호(password)를 확보
        I2FormAuthenticationToken authToken = (I2FormAuthenticationToken) authentication;

        if (authToken.getPrincipal() == null || "".equals(authToken.getPrincipal())) {
            throw new InsufficientAuthenticationException("인증정보가 없어 인증에 실패하였습니다.");
        }

        if (authToken.getCredentials() == null || "".equals(authToken.getCredentials())) {
            throw new InsufficientAuthenticationException("인증정보가 없어 인증에 실패하였습니다.");
        }
    }

    /**
     * 인증 결과를 처리한다.
     * 
     * @param authentication
     *            인증객체 정보
     * @param authResult
     *            인증 결과
     * @param accessIp
     *            접근 IP
     * @return 성공적으로 인증이 됐을 경우 사용자 인증 결과 토큰을 생성하여 반환한다.
     */
    protected Authentication processAuthResult(Authentication authentication, AuthResult authResult, String accessIp) {
        // 인증결과 처리
        String resultCode = authResult.getResult();
        if (AuthResult.STOPPAGE.equals(resultCode)) {
            throw new DisabledException("사용이 중지되었습니다.");
        }
        if (AuthResult.NO_LOGINID.equals(resultCode)) {
            throw new AuthenticationCredentialsNotFoundException("사용자 정보가 일치하지 않습니다.");
        }
        if (AuthResult.PASSWORD_ERROR.equals(resultCode)) {
            throw new BadCredentialsException("사용자 정보가 일치하지 않습니다.");
        }
        if (AuthResult.EXPIRED.equals(resultCode)) {
            throw new BadCredentialsException("사용 기간이 만료되어 더 이상 시스템을 사용하실 수 없습니다.");
        }
        if (AuthResult.NOT_AUTHORIZED_IP.equals(resultCode)) {
            throw new BadCredentialsException("해당 IP에서는 접속하실 수 없습니다.");
        }
        if (!AuthResult.NORMAL.equals(resultCode)) {
            throw new AuthenticationServiceException("사용자 정보가 일치하지 않습니다.");
        }

        // 로그인 한 사용자 정보 조회 후 인증토큰 생성
        // 이 때 접근 IP도 설정
        UserAccountBDT user = authResult.getUser();
        LoginUser loginUser = new LoginUser(user, accessIp);

        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(loginUser,
                authentication.getCredentials(), null);
        return result;
    }

    /**
     * @param aAthenticationService
     *            설정할 authenticationService
     */
    public void setAuthenticationService(AuthenticationService aAthenticationService) {
        this.authenticationService = aAthenticationService;
    }

}
