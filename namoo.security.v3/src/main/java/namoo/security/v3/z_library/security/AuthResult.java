
package namoo.security.v3.z_library.security;

import java.io.Serializable;

import namoo.security.v3.domain.user.entity.UserAccountBDT;

/**
 * TODO 위치 결정해야함
 * 로그인 인증 결과를 처리하기 위한 클래스이다. 로그인 수행 결과가 성공했는지,
 * 아이디가 사용중지 상태인지, 존재하지 않는 아이디인지, 비밀번호 오류인지등의 로그인 수행결과를 나타낸다.
 *
 * @author Bee
 * @since 2009. 07. 07
 */
public class AuthResult implements Serializable {

    /**
	 *
	 */
	private static final long serialVersionUID = -1796145811939374307L;
	/**
     * 정상
     */
    public static final String NORMAL = "N";
    /**
     * 사용중지
     */
    public static final String STOPPAGE = "S";
    /**
     * 로그인 아이디 없음
     */
    public static final String NO_LOGINID = "I";
    /**
     * 비밀번호 오류
     */
    public static final String PASSWORD_ERROR = "P";
    /**
     * 사용기간 만료
     */
    public static final String EXPIRED = "E";
    /**
     * 접근권한이 없는 IP
     */
    public static final String NOT_AUTHORIZED_IP = "A";



    /** 로그인 결과 */
    private String result;

    /** 사용자번호 */
    private Long userNo;

    /** 사용자정보 */
    private UserAccountBDT user;

    /**
     * 기본생성자
     */
    public AuthResult() {
    }

    /**
     * 생성자
     * @param result 인증결과
     */
    public AuthResult(String result) {
        this.result = result;
    }

    /**
     * @return 로그인 결과
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result
     *            설정할 로그인 결과
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @return 사용자번호
     */
    public Long getUserNo() {
        return userNo;
    }

    /**
     * @param userNo
     *            설정할 사용자번호
     */
    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    /**
     * @return 사용자정보 (로그인 성공 시에만 존재)
     */
    public UserAccountBDT getUser() {
        return user;
    }

    /**
     * @param user 설정할 사용자정보
     */
    public void setUser(UserAccountBDT user) {
        this.user = user;
    }
}
