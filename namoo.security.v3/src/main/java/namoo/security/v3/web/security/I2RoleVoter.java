package namoo.security.v3.web.security;

import org.springframework.security.Authentication;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.vote.RoleVoter;

/**
 * 로그인 되지 않은 사용자만 본 voter를 통해 거부됨. 
 * 
 * @author 김동열
 * @since 2008. 10. 24
 */
public class I2RoleVoter extends RoleVoter {
    /**
     * 접근여부 판단.
     * 
     * {@inheritDoc}}
     */
    @Override
    public int vote(Authentication authentication, Object object, ConfigAttributeDefinition config) {
        return ACCESS_DENIED;
    }
}
