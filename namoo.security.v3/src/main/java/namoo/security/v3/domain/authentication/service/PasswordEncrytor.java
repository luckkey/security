
package namoo.security.v3.domain.authentication.service;

/**
 * 비밀번호 암호화 인터페이스, 사용자 비밀번호를 암호하는 기능을 제공한다. 
 * 기본적으로 SHA-1 해시 방식으로 비밀번호 암호화하며, 한 번 암호화된 비밀번호는 복호화할 수 없다.
 * 
 *
 * @author 김동열
 * @since 2008. 11. 17
 */
public interface PasswordEncrytor {

    /**
     * 비밀번호를 암호화한다.
     * @param password 암호화되기 전 비밀번호
     * @return 암호화된 후 비밀번호
     */
    String encript(String password);
    
    /**
     * 암호화 알고리즘 변경한다.
     * 
     * @param algorithm 암호화 알고리즘
     */
    void setAlgorithm(String algorithm);
}
