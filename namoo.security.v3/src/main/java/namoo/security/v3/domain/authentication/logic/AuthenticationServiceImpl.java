package namoo.security.v3.domain.authentication.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import namoo.security.v3.domain.authentication.entity.PMSMenu;
import namoo.security.v3.domain.authentication.entity.ResourceOrganizer;
import namoo.security.v3.domain.authentication.service.AuthenticationService;
import namoo.security.v3.domain.authentication.store.MenuStore;
import namoo.security.v3.domain.user.entity.AccountBDT;
import namoo.security.v3.domain.user.entity.UserAccountBDT;
import namoo.security.v3.domain.user.store.UserStore;
import namoo.security.v3.z_library.security.AuthResult;
import namoo.security.v3.z_library.security.SessionInfo;

/**
 * 
 * 사용자 인증 처리을 유틸리티 컴포넌트의 인터페이스 구현체이다. 인증 처리를 위한 사용자 ID 및 비밀번호 확인을 위한 인터페이스 및 시스템 접근 로그, 로그아웃 처리와 같은 기능을 제공한다.
 * 
 * @author Bee
 * @since 2009. 07. 07
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    
    @Autowired
    private UserStore userStore;
    
    @Autowired
    private MenuStore menuStore;

    /**
     * {@inheritDoc}
     */
    public AuthResult authenticate(String loginId, String password, SessionInfo session) {
        
        UserAccountBDT auth = userStore.readUserAccountByLoginId(loginId);
        // 로그인 아이디 없음
        if (auth == null) {
            return new AuthResult(AuthResult.NO_LOGINID);
        }

        // 사용중지
        if (auth.getAccount().getStatus().equals(AccountBDT.STATUS_STOP)) {
            return new AuthResult(AuthResult.STOPPAGE);
        }

        if (!session.isSSOAuthenticated()) {
            // 비밀번호 틀림
            // TODO 암호화 객체 가져오기
            if (!auth.getAccount().getPassword().equals(new MDPasswordEncrytor().encript(password.trim()))) {
                return new AuthResult(AuthResult.PASSWORD_ERROR);
            }
        }

        // 사용기간 만료
        if (auth.getAccount().isExpired()) {
            return new AuthResult(AuthResult.EXPIRED);
        }

        // 접근권한이 없는 IP TODO 사용자 IP 테이블을 추가해야 가능 - 추후 고려
        //        if (!isAccessableIP(auth.getUser().getUserNo(), session)) {
        //            return new AuthResult(AuthResult.NOT_AUTHORIZED_IP);
        //        }

        return success(auth, session);
    }
    
    /**
     * (인증 성공) 인증 결과를 반환한다.
     * @param account 사용자 계정정보
     * @param session 세션 정보
     * @return 인증 결과
     */
    private AuthResult success(UserAccountBDT account, SessionInfo session) {

        // 인증성공
        AuthResult result = new AuthResult(AuthResult.NORMAL);

        result.setUserNo(account.getUser().getUserNo());
        result.setUser(account);

        // TODO 로그인기록
        // if (readLoginLog(auth.getUser().getUserNo(), session) == null) {
        // createLoginLog(session, auth.getUser().getUserNo());
        // }

        return result;
    }

    @Override
    public PMSMenu inquireMenuByRoles(List<String> roleIds) {
        //
        List<PMSMenu> authorityMenus = new ArrayList<PMSMenu>();
        for (String roleId : roleIds) {
            List<PMSMenu> menus = menuStore.readMenuListByRole(roleId);
            if (menus!=null) {
                authorityMenus.addAll(menus);
            }
        }
        
        return new ResourceOrganizer(authorityMenus).getRootMenu();
    }
    
    
}
