package namoo.security.v3.z_library;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

/**
 * 암복호화기 (RSA 알고리즘)
 * @author sgyoum@nextree.co.kr
 */
public class RSACrypto {
	
	/**
	 * 암호화 (복호화 테스트용)
	 * @param message
	 * @param publicKeyModulusHexString 공개키 모듈
	 * @param publicKeyExponentHexString 공개키  
	 * @return HexString으로 반환받음
	 */
	public static String encrypt(String message ,String publicKeyModulusHexString ,String publicKeyExponentHexString){
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(new BigInteger(publicKeyModulusHexString , 16),new BigInteger(publicKeyExponentHexString , 16));
			RSAPublicKey pubKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
			
			Cipher cipher= Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE , pubKey );
			
			return byteArrayToHex(cipher.doFinal(message.getBytes("UTF-8")));
		} catch (Exception e) {
			throw new VizendException("암호화 알고리즘을 지원하지 않습니다." , e);
		}
	}
	
	/**
	 * 복호화
	 * @param encryptedMessage 암호화된 메세지
	 * @param privateKey 개인키
	 * @return
	 */
	public static String decrypt(String encryptedMessage , PrivateKey privateKey){
		try {
			Cipher cipher= Cipher.getInstance("RSA/ECB/PKCS1Padding");
	        cipher.init(Cipher.DECRYPT_MODE, privateKey);
	        byte[] encryptedByte = hexToByteArray(encryptedMessage);
	        byte[] doFinal = cipher.doFinal(encryptedByte);
	        String decryptedValue = new String(doFinal , "UTF-8"); // 문자 인코딩 주의.
	        return decryptedValue;
		} catch (Exception e) {
			throw new VizendException("암호화 알고리즘을 지원하지 않습니다." , e);
		}
	}
	
	/**
	 * 복호화
	 * @param encryptedMessage 암호화된 메세지
	 * @param privateKey 개인키
	 * @return
	 */
	public static String decrypt(String encryptedMessage , String publicKeyModulusHexString ,String publicKeyExponentHexString){
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			
			RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(new BigInteger(publicKeyModulusHexString), new BigInteger(publicKeyExponentHexString));
			RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
			Cipher cipher= Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			
			byte[] encryptedByte = hexToByteArray(encryptedMessage);
			byte[] doFinal = cipher.doFinal(encryptedByte);
			String decryptedValue = new String(doFinal, "UTF-8"); // 문자 인코딩 주의.
			return decryptedValue;
			
		} catch (Exception e) {
			throw new VizendException("암호화 알고리즘을 지원하지 않습니다." , e);
		}
	}
	
	/**
     * 16진 문자열을 byte 배열로 변환한다.
     */
    private static byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() % 2 != 0) {
            return new byte[]{};
        }

        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            byte value = (byte)Integer.parseInt(hex.substring(i, i + 2), 16);
            bytes[(int) Math.floor(i / 2)] = value;
        }
        return bytes;
    }
    
    private static String byteArrayToHex(byte[] ba) {
        if (ba == null || ba.length == 0) {
            return null;
        }
     
        StringBuffer sb = new StringBuffer(ba.length * 2);
        String hexNumber;
        for (int x = 0; x < ba.length; x++) {
            hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
     
            sb.append(hexNumber.substring(hexNumber.length() - 2));
        }
        return sb.toString();
    } 
    
}
