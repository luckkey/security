package namoo.security.v3.z_library;

import java.io.Serializable;

/**
 * 암호화 메세지 포멧<br>
 * 비밀번호 전송 방법 
 * @author sgyoum@nextree.co.kr
 */
public class SecurityMessageFormat implements Serializable {
	
	private static final long serialVersionUID = -1794929697723595110L;
	
	public static String FORMAT_RSA = "RSA";
	public static String FORMAT_TEXT = "TEXT";
	public static String FORMAT_BASE64 = "BASE64";
	
	private static SecurityMessageFormat instance;

	private String format = FORMAT_TEXT;
	
	public static SecurityMessageFormat getInstance(){
		if(instance == null) instance = new SecurityMessageFormat();
		return instance;
	}

	public String getFormat() {
		return format;
	}
	
	public static String getSecurityMessageFormat() {
		return getInstance().format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
}
