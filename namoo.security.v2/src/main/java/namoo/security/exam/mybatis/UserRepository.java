package namoo.security.exam.mybatis;

import namoo.security.exam.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private UserMapper userMapper;

    public User readUserByUserId(String userId) {
        return this.userMapper.selectUserByUserId(userId);
    }
}

