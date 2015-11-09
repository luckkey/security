package namoo.security.v3.service.user.da.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import namoo.security.v3.domain.user.entity.AccountBDT;
import namoo.security.v3.domain.user.entity.UserAccountBDT;
import namoo.security.v3.domain.user.entity.UserBDT;
import namoo.security.v3.domain.user.store.UserStore;
import namoo.security.v3.service.user.da.mybatis.mapper.UserMapper;
import namoo.security.v3.z_library.VizendException;

@Repository
public class UserMyBatisStore implements UserStore {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserBDT readUser(Long userNo) {
        //
        return userMapper.selectUser(userNo);
    }

    @Override
    public UserAccountBDT readUserAccountByLoginId(String loginId) {
        // TODO 리팩토링 필요
        if (loginId == null) {
            throw new VizendException("로그인 아이디가 없어 조회 할 수 없습니다.");
        }

        AccountBDT account = readAccountByLoginId(loginId);
        if (account == null) {
            return null;
        }

        UserAccountBDT userAccount = new UserAccountBDT();
        userAccount.setUser(readUser(account.getUserNo()));
        userAccount.setAccount(account);

        return userAccount;

    }

    private AccountBDT readAccountByLoginId(String loginId) {
        //
        return userMapper.selectAccountByLoginId(loginId);
    }

}
