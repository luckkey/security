package namoo.security.exam.domain.user;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * 사용자계정
 */
public class AccountBDT implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -637552452080798953L;
	/** 사용자 상태 - 사용중 */
	public static final String STATUS_RUN = "STS_RUN";
	/** 사용자 상태 - 사용중지 */
	public static final String STATUS_STOP = "STS_STP";
	/** 사용자 상태 - 사용만료 */
	public static final String STATUS_EXPIRED = "STATUS_EXPIRED";

	/** 사용자 */
	private Long userNo;

	/** 로그인아이디 */
	private String loginId;

	/** 비밀번호 */
	private String password;

	/** 상태 */
	private String status;

	/** 만료일 */
	private Date expiredDate;

	/** 비밀번호 변경일 */
	private Date passwordChangeDate;

	/** 언어 */
	private String language;

	/** 시간대 아이디 */
	private String timeZoneId;

	/**예약필드(외부계정 연계여부) */
	private String reserved;
	
	/** 계정 타입 */
	private String accountType;

	// -------------------------------------------------------------------------

	/**
	 * @return 로그인아이디
	 */
	public String getLoginId() {
		return loginId;
	}

	public Long getUserNo() {
		return userNo;
	}

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

	/**
	 * @param loginId
	 *            로그인아이디
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return 비밀번호
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            비밀번호
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return 상태
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            상태
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return 만료일
	 */
	public Date getExpiredDate() {
		return expiredDate;
	}

	/**
	 * @param expiredDate
	 *            만료일
	 */
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	/**
	 * @return 비밀번호 변경일
	 */
	public Date getPasswordChangeDate() {
		return passwordChangeDate;
	}

	/**
	 * @param passwordChangeDate
	 *            비밀번호 변경일
	 */
	public void setPasswordChangeDate(Date passwordChangeDate) {
		this.passwordChangeDate = passwordChangeDate;
	}

	/**
	 * 기간 만료여부 확인
	 *
	 * @return 만료여부 : true-만료, false-만료되지 않음.
	 */
	public boolean isExpired() {

		if (expiredDate == null) {
			return false;
		}

		Date today = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.expiredDate);
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);

		if (today.getTime() > calendar.getTimeInMillis()) {
			return true;
		}

		return false;
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

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}
