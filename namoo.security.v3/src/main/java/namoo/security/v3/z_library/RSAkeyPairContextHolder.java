package namoo.security.v3.z_library;

/**
 * RSAPair를 Thread local에 저장한다.
 * @author sgyoum@nextree.co.kr
 */
public class RSAkeyPairContextHolder {
	
	private static ThreadLocal<RSAkeyPair> contextHolder = new ThreadLocal<RSAkeyPair>();
	
	public static void clearContext() {
        contextHolder.set(null);
    }

    public static  RSAkeyPair getContext() {
        if (contextHolder.get() == null) {
            return null;
        }
        return contextHolder.get();
    }

    public static void setContext(RSAkeyPair context) {
    	contextHolder.set(context);
    }
    
    public static boolean hasContext() {
    	 if (contextHolder.get() == null) {
             return false;
         }
         return true;
    }
    
}
