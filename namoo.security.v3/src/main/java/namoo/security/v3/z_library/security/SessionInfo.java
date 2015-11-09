package namoo.security.v3.z_library.security;

import java.io.Serializable;

/**
 * 사용자가 시스템에 로그인 시에 생성되는 세션정보를 보관하기 위한 클래스로 보관된 정보는 데이터베이스에 저장되고, 접근 권한 정보 판단에
 * 사용된다
 *
 * @author Bee
 * @since 2009. 07. 07
 */
public class SessionInfo implements Serializable {

	private static final long serialVersionUID = -7424095106493529630L;

	/** 접근세션 키 */
	private String sessionKey;
	/** 접근 IP */
	private String accessIp;

	/** SSO인증되었는지 여부 */
	private boolean isSSOAuthenticated;

	/**
	 * 기본 생성자
	 */
	public SessionInfo() {
	}

	/**
	 * 생성자
	 *
	 * @param sessionKey
	 *            접근세션 키
	 * @param accessIp
	 *            접근 IP
	 */
	public SessionInfo(String sessionKey, String accessIp) {
		this.sessionKey = sessionKey;
		this.accessIp = accessIp;
	}

	/**
	 * @return 접근세션 키
	 */
	public String getSessionKey() {
		return sessionKey;
	}

	/**
	 * @param sessionKey
	 *            설정할 접근세션 키
	 */
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	/**
	 * @return 접근 IP
	 */
	public String getAccessIp() {
		return accessIp;
	}

	/**
	 * @param accessIp
	 *            설정할 접근 IP
	 */
	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}

	/**
	 * @return the isSSOAuthenticated
	 */
	public boolean isSSOAuthenticated() {
		return isSSOAuthenticated;
	}

	/**
	 * @param isSSOAuthenticated
	 *            the isSSOAuthenticated to set
	 */
	public void setSSOAuthenticated(boolean isSSOAuthenticated) {
		this.isSSOAuthenticated = isSSOAuthenticated;
	}
}
