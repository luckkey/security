
package namoo.security.v3.domain.authentication.logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import namoo.security.v3.domain.authentication.service.PasswordEncrytor;
import namoo.security.v3.z_library.Base64;
import namoo.security.v3.z_library.VizendException;

/**
 * SHA-1 이나 MD5 알고리즘을 이용한 비밀번호 암호화 기능을 제공한다.
 *
 * @author 김동열
 * @since 2008. 11. 17
 */
public class MDPasswordEncrytor implements PasswordEncrytor {

    /**
     * 알고리즘, MD5, SHA-1
     */
    private String algorithm = "SHA-1";

    public void setAlgorithm(String algorith) {
        this.algorithm = algorith;
    }

    public String encript(String password) {

        if (password == null) {
            return null;
        }

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new VizendException("지원하지 않는 알고리즘입니다: {0}", e, this.algorithm);
        }
        digest.reset();
        digest.update(password.getBytes());
        return Base64.encodeBytes(digest.digest());
    }

}
