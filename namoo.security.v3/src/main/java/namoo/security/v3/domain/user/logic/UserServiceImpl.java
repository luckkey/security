package namoo.security.v3.domain.user.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import namoo.security.v3.domain.user.entity.UserAccountBDT;
import namoo.security.v3.domain.user.service.UserService;
import namoo.security.v3.domain.user.store.UserStore;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserStore userStore;

    /**
     * {@inheritDoc}
     */
    public UserAccountBDT readUserAccountByLoginId(String loginId) {
        UserAccountBDT userAccount = userStore.readUserAccountByLoginId(loginId);
        if (userAccount != null && userAccount.getUser() != null) {
            // TODO
            // addCompanyAndDepartmentInfo(userAccount.getUser());
        }

        return userAccount;
    }
}
