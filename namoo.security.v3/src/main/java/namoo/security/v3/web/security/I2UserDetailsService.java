package namoo.security.v3.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

import namoo.security.v3.domain.user.entity.UserAccountBDT;
import namoo.security.v3.domain.user.service.UserService;

public class I2UserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    // 여기서 username은 login id를 말함.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        UserAccountBDT userAccount = userService.readUserAccountByLoginId(username);
        if (userAccount == null)
            throw new UsernameNotFoundException("User account not found");
        return new LoginUser(userAccount, null);
    }

     public void setUserService(UserService userService) {
     this.userService = userService;
     }

}
