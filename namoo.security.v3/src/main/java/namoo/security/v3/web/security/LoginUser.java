package namoo.security.v3.web.security;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;

import namoo.security.v3.domain.user.entity.UserAccountBDT;
import namoo.security.v3.z_library.DateUtil;

/**
 * 로그인 한 사용자 세션 정보를 조회하는 유틸리티.
 * <p>
 * 획득할 수 있는 정보
 * <ul>
 * <li>로그인아이디</li>
 * <li>사용자번호</li>
 * <li>사용자 이름</li>
 * <li>사용자 역할명</li>
 * <li>사용자 구분</li>
 * <li>기본페이지</li>
 * <li>접속아이피</li>
 * </ul>
 * </p>
 * 
 * <p>
 * 사용예
 * 
 * <pre>
 * String loginId = LoginUser.getLoginUsername();
 * String accessIp = LoginUser.getLoginAccessIp();
 * ...
 * </pre>
 * 
 * </p>
 * 
 * 
 * @author Bee
 * @since 2009. 07. 07
 */
public class LoginUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    /** 사용자번호 */
    private Long userNo;
    /** 로그인아이디 */
    private String loginId;
    /** 사용자이름 */
    private String realName;
    // /** 사용자 구분 */
    // private String kind;
    /** 비밀번호 */
    private String password;
    /** 접속아이피 */
    private String accessIp;
    /** 비밀번호 변경일 */
    private Date passwordChangeDate;
    /** 비밀번호 변경창 표시여부 */
    private boolean dispalyPasswordChange = true;
    /** 사용자 등록일 */
    private Date registDate;

    private String language;
    private String timeZoneId;
    private Locale locale;
    private TimeZone timeZone;

    private String nickName;

    /**
     * 로그인 사용자 객체 생성을 위한 생성자.
     * 
     * @param userAccount
     *            사용자정보
     * @param aAccessIp
     *            접근 IP 주소
     */
    public LoginUser(UserAccountBDT userAccount, String aAccessIp) {

        // 사용자 기본정보
        this.userNo = userAccount.getUser().getUserNo();
        this.loginId = userAccount.getAccount().getLoginId();
        this.realName = userAccount.getUser().getName();
        // this.kind = userAccount.getKind();
        this.password = userAccount.getAccount().getPassword();
        this.accessIp = aAccessIp;
        this.passwordChangeDate = userAccount.getAccount().getPasswordChangeDate();
        this.registDate = userAccount.getUser().getRegDate();
        this.language = userAccount.getAccount().getLanguage();
        this.timeZoneId = userAccount.getAccount().getTimeZoneId();

        this.nickName = userAccount.getUser().getNickname();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.acegisecurity.userdetails.UserDetails#getPassword()
     */
    public String getPassword() {
        return this.password;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.acegisecurity.userdetails.UserDetails#getUsername()
     */
    public String getUsername() {
        return this.loginId;
    }

    public String getRealName() {
        return this.realName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.acegisecurity.userdetails.UserDetails#isAccountNonExpired()
     */
    public boolean isAccountNonExpired() {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.acegisecurity.userdetails.UserDetails#isAccountNonLocked()
     */
    public boolean isAccountNonLocked() {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.acegisecurity.userdetails.UserDetails#isCredentialsNonExpired()
     */
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.acegisecurity.userdetails.UserDetails#isEnabled()
     */
    public boolean isEnabled() {
        return true;
    }

    /**
     * ------------------------------------------------------------------------ ------
     * 
     * 
     * 
     * static method area
     * 
     * 
     * 
     * ------------------------------------------------------------------------ --------
     */

    /**
     * 로그인한 사용자 정보 조회
     * 
     * @return 로그인한 사용자 정보
     */
    public static LoginUser getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }

        Object result = authentication.getPrincipal();
        if (result instanceof LoginUser) {
            return (LoginUser) result;
        }

        return null;
    }

    /**
     * 로그인 한 상태인지 검사
     * 
     * @return 로그인한 상태라면 true, 아니면 false
     */
    public static boolean isLogin() {
        return getLoginUser() != null;
    }

    /**
     * 로그인 한 사용자 로그인아이디 조회
     * 
     * @return 로그인아이디
     */
    public static String getLoginUsername() {
        LoginUser loginUser = getLoginUser();
        return loginUser != null ? loginUser.getUsername() : null;
    }

    /**
     * 로그인 한 사용자번호 조회
     * 
     * @return 사용자번호
     */
    public static Long getLoginUserNo() {
        LoginUser loginUser = getLoginUser();
        return loginUser != null ? loginUser.userNo : null;
    }

    /**
     * 로그인 한 사용자 닉네임 조회
     * 
     * @return 로그인아이디
     */
    public static String getLoginUserNickname() {
        LoginUser loginUser = getLoginUser();
        return loginUser != null ? loginUser.nickName : null;
    }

    /**
     * 로그인 한 사용자의 이름 조회
     * 
     * @return 사용자 이름
     */
    public static String getLoginRealName() {
        LoginUser loginUser = getLoginUser();
        return loginUser != null ? loginUser.realName : null;
    }

    /**
     * 로그인 한 사용자의 구분
     * 
     * @return 사용자 구분
     */
    public static String getLoginUserKind() {
        throw new RuntimeException("Not supported operation!!!");
        // LoginUser loginUser = getLoginUser();
        // return loginUser != null ? loginUser.kind : null;
    }

    /**
     * 사용자가 로그인한 접속 아이피
     * 
     * @return 접속 아이피
     */
    public static String getLoginAccessIp() {
        LoginUser loginUser = getLoginUser();
        return loginUser != null ? loginUser.accessIp : null;
    }

    public static String getLoginNickName() {
        LoginUser loginUser = getLoginUser();
        return loginUser != null ? loginUser.nickName : null;
    }

    /**
     * 비밀번호 유효기간
     */
    private final static Long PASSWORD_AVAILABLE_DAY = 90L;

    /**
     * 사용자 비밀번호 변경일
     * 
     * @return
     */
    public static boolean isPasswordExpired() {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return false;
        }

        if (loginUser.passwordChangeDate == null) {
            return DateUtil.getDuration(loginUser.registDate, new Date()) > PASSWORD_AVAILABLE_DAY;

        } else if (DateUtil.getDuration(loginUser.passwordChangeDate, new Date()) > PASSWORD_AVAILABLE_DAY) {
            return true;
        }

        return false;

    }

    /**
     * 비밀번호 변경창 표시여부를 반환한다.
     * 
     * @return
     */
    public static boolean displayPasswordChangeWindow() {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return false;
        }

        return loginUser.dispalyPasswordChange;
    }

    /**
     * 비밀번호 변경창 표시여부를 비활성화한다.
     * 
     * @return
     */
    public static void disablePasswordChangeWindow() {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return;
        }

        loginUser.dispalyPasswordChange = false;
    }

    /**
     * 사용자의 지역정보(언어,국가)정보를 추출한다.
     * 
     * @return
     */
    public static Locale getLoginUserLocale() {
        LoginUser loginUser = getLoginUser();
        // 없으면 기본 언어를 리턴하면 곤란하다. 브라우저 설정언어를 먼저 골라야 한다.
        // I18NTag 참조 (현재 이 메소드 사용하는 java, jsp가 너무 많다)
        return (loginUser != null ? loginUser.getLocale() : Locale.getDefault());
    }

    /**
     * 사용자의 시간대정보를 추출한다.
     * 
     * @return
     */
    public static TimeZone getLoginUserTimeZone() {
        LoginUser loginUser = getLoginUser();
        return (loginUser != null ? loginUser.getTimeZone() : TimeZone.getDefault());
    }

    public static void setLoginUserNickname(String nickname) {
        LoginUser loginUser = getLoginUser();
        if (loginUser != null)
            loginUser.setNickName(nickname);
    }

    public static void setLoginUserLocale(Locale locale) {
        LoginUser loginUser = getLoginUser();
        if (loginUser != null)
            loginUser.setLocale(locale);
    }

    public static void setLoginUserTimeZone(TimeZone timeZone) {
        LoginUser loginUser = getLoginUser();
        if (loginUser != null)
            loginUser.setTimeZone(timeZone);
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public GrantedAuthority[] getAuthorities() {
         return UnitRoleContext.getAuthorities();
    }

}
