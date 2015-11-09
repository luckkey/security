package namoo.security.v3.z_library;

import java.io.Serializable;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Date;
import java.util.UUID;

/**
 * RSA keypair
 * @author sgyoum@nextree.co.kr
 */
public class RSAkeyPair implements Serializable {
	
	private static final long serialVersionUID = -1132264544043692450L;
	
	private String publicKeyExponent;
	private String publicKeyModulus;
	
	private PrivateKey privateKey;
	private PublicKey publicKey;
	
	private String keyPairId;
	
	private boolean used = false;
	
	private Date createdDate;
	
	private RSAkeyPair(){
		
		try {
			
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(512);
			
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			KeyPair keyPair = generator.generateKeyPair();
			
			RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(keyPair.getPublic(), RSAPublicKeySpec.class);
			publicKeyModulus = publicSpec.getModulus().toString(16);
			publicKeyExponent = publicSpec.getPublicExponent().toString(16);
			
			privateKey = keyPair.getPrivate();
			publicKey = keyPair.getPublic();
			
			keyPairId = UUID.randomUUID().toString();
			createdDate = new Date();
		} catch (NoSuchAlgorithmException e) {
			throw new VizendException("암호화 알고리즘을 지원하지 않습니다." , e);
		} catch (InvalidKeySpecException e) {
			throw new VizendException("암호화 알고리즘을 지원하지 않습니다." , e);
		}
	}
	
	/**
	 * RSAkeyPair를 생성한다.
	 * @return
	 */
	public static RSAkeyPair generateKeyPair(){
		return new RSAkeyPair();
	}
	
	/**
	 * HexString 으로 PublicKeyModulus 반환
	 * @return
	 */
	public String getPublicKeyModulus() {
		return publicKeyModulus;
	}
	
	/**
	 * HexString 으로 PublicKeyExponent 반환
	 * @return
	 */
	public String getPublicKeyExponent() {
		return publicKeyExponent;
	}

	/**
	 * 개인키 반환
	 * @return
	 */
	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	
	/**
	 * 공개키 반환
	 * @return
	 */
	public PublicKey getPublicKey() {
		return publicKey;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}
	
	public String getKeyPairId() {
		return keyPairId;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
}
