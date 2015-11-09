package namoo.security.v3.z_library;

import java.text.MessageFormat;

/**
 * Vizend 표준 예외
 * 
 * @author 김동열
 */
public class VizendException extends RuntimeException {

	private static final long serialVersionUID = 7576635966751348981L;
	
	private Object[] args;
	
	/**
	 * 생성자
	 * 
	 * @param message 에러메시지
	 */
	public VizendException(String message) {
		super(message);
	}
	
	/**
	 * 생성자
	 * 
	 * @param message 에러메시지
	 * @param args 에러메시지 아규먼트
	 */
	public VizendException(String message, Object... args) {
		super(message);
		this.args = args;
	}
	
	/**
	 * 생성자
	 * 
	 * @param message 에러메시지
	 * @param cause 원인 에러
	 */
	public VizendException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * 생성자
	 *  
	 * @param message 에러메시지
	 * @param args 에러메시지 아규먼트
	 * @param cause 원인 에러
	 */
	public VizendException(String message, Throwable cause, Object... args) {
		super(message, cause);
		this.args = args;
	}
	
	@Override
	public String getMessage() {
		if (this.args == null) return super.getMessage();
		return MessageFormat.format(super.getMessage(), this.args);
	}

	/**
	 * 포맷되지 않은 원래 에러 메시지
	 * 
	 * @return 포맷되지 않은 원래 에러 메시지 반환
	 */
	public String getUnformattedMessage() {
		return super.getMessage();
	}
}
