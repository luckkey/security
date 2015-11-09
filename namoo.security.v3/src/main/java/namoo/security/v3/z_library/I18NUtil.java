package namoo.security.v3.z_library;

import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

/**
 * 국제화 지원 유틸리티<br>
 * 
 * @author 윤기율
 * @since 2010. 10. 27.
 */
public class I18NUtil {
	/**
	 * 사용자의 지역정보를 추출한다.<br>
	 * 사용자의 요청 헤더로 부터 언어를 확인하여 지역정보를 제공함.<br>
	 * 없을경우, 기본 시스템 지역정보를 제공함.
	 * 
	 * @param request
	 *            요청객체
	 * @return 사용자의 지역정보
	 */
	public static Locale getUserLocale(HttpServletRequest request) {
		return getUserLocale(getAcceptLanguage(request));
	}

	/**
	 * 사용자의 지역정보를 추출한다.<br>
	 * <ol>
	 * <li>사용자의 언어정보 즉 language파라미터를 확인하여 있을경우에는 사용자의 언어정보를 기반으로 지역정보를 제공함.
	 * <li>사용자의 언어정보가 없을경우 요청 헤더로 부터 언어를 확인하여 지역정보를 제공함.
	 * </ol>
	 * 없을경우, 기본 시스템 지역정보를 제공함.
	 * 
	 * @param request
	 *            요청객체
	 * @param language
	 *            사용자의 언어정보
	 * @return 사용자의 지역정보
	 */
	public static Locale getUserLocale(HttpServletRequest request, String language) {
		if (!StringUtil.isNullOrNullString(language))
			return getUserLocale(language);

		return getUserLocale(getAcceptLanguage(request));
	}
	
	/**
	 * 언어에 해당하는 지역정보를 제공한다.
	 * 
	 * @param language
	 *            언어정보
	 * @return 지역정보
	 */
	public static Locale getUserLocale(String language) {
		if (Locale.KOREA.getLanguage().equals(language)) {
				return Locale.KOREA;
		}
		return Locale.US;
	}
	
	/**
	 * 요청 헤더로 부터 Accept-Language을 추출한다.<br>
	 * 브라우저 별로 아래와 같은 형태로 가져온다<br>
	 * <ul>
	 * <li>(Firefox) ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3
	 * <li>(IE) ko, en
	 * </ul>
	 * 
	 * @param request
	 *            요청객체
	 * @return 언어정보
	 */
	private static String getAcceptLanguage(HttpServletRequest request) {
		String acceptLang = request.getHeader("Accept-Language");
		if (StringUtil.isNullOrNullString(acceptLang))
			return null;
		String firstLang = acceptLang.split(",")[0];
		if (firstLang.indexOf(Locale.KOREA.getLanguage()) > -1) {
			return Locale.KOREA.getLanguage();
		}

		return Locale.US.getLanguage();
	}


	/**
	 * 사용자의 시간대정보를 추출한다.<br>
	 * 사용자의 시간대 아이디가 없을경우, 기본 시스템 시간대정보를 제공함.
	 * 
	 * @param timeZoneId
	 *            사용자의 시간대 아이디
	 * @return 사용자의 시간대정보
	 */
	public static TimeZone getUserTimeZone(String timeZoneId) {
		if (!StringUtil.isNullOrNullString(timeZoneId))
			return TimeZone.getTimeZone(timeZoneId);
		return TimeZone.getDefault();
	}

}
