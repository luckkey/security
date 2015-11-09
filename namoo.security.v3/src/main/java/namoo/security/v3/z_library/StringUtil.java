package namoo.security.v3.z_library;

/**
 * 문자열 처리 유틸리티
 *
 * @author 윤기율
 * @since 2008. 12. 11
 */
public class StringUtil {

    /**
     * 입력된 문자열의 newline(\n)을 HTML &lt;br/&gt;태그로 변경한다.
     *
     * @param source
     * @return
     */
    public static String convertNewLineToBrTag(String source) {
        return source.replaceAll("(\r\n|\r|\n|\n\r)", "<br/>");
    }

    public static int countStringLineNumber(String source) {
        if (source == null || source.length() <= 0)
            return 0;
        return source.split("(\r\n|\r|\n|\n\r)").length;
    }

    /**
     * 입력된 문자열이 <,>를 HTML &lt;,&gt;로 변경한다.
     * @param source
     * @return
	 * @author 박윤미
     */
    public static String convertHtmlToText(String source) {
        return source.replace("<", "&lt;").replace(">", "&gt;");
    }

    /*
     * Added by 박윤미 (2009.01.11)
     * @author 임병인
     */

    /**
     * @param str
     *            문자열(널이 입력될 수 있다.)
     * @return 널이라면 ""로 대체되고,trim처리된 문자열
     */
    public static String nullToSpaceTrim(String str) {
        return (str == null) ? "" : str.trim();
    }

    /**
     * 입력된 문자열이 널이면 널스트링("")을 반환하고 널이아니면 입력된 문자열을 반환한다.
     *
     * @param str
     *            문자열(널이 입력될 수 있다.)
     * @return 널스트링("") 또는 입력된 문자열
     */
    public static String nullToString(String str) {
        return (str == null) ? "" : str;
    }

    /**
     * 입력된 문자열이 널이거나 널스트링("")인지 여부를 반환한다.
     *
     * @param str
     *            문자열
     * @return 입력된 문자열이 널이거나 널스트링("")이면 true, 아니면 false
     * @author 임병인
     * @see #isTrimmedNullString(String) 문자열을 trim하여 비교할 때 사용
     */
    public static boolean isNullOrNullString(String str) {
        return ((null == str) || (str.length() <= 0));
    }

    /**
     * 입력된 문자열이 널이거나 trim된 문자열이 널스트링("")인지 여부를 반환한다.
     *
     * @param str
     *            문자열
     * @return 입력된 문자열이 널이거나 trim된 문자열이 널스트링("")이면 true, 아니면 false
     * @see #isNullSpace(String) 와 동일한 기능을 수행하며 메소드의 명료성을 위해 정의함.
     */
    public static boolean isTrimmedNullString(String str) {
        return ((null == str) || (str.length() <= 0) || (str.trim().length() <= 0));
    }

    /**
     * 객체를 리플렉션하여 내부 변수들을 출력한다.
     * @param obj
     * @return
     */
//    public static String toString(Object obj) {
//        return ToStringBuilder.reflectionToString(obj);
//    }

}
