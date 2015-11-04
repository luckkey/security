package namoo.security.exam.security;

import namoo.security.exam.domain.User;
import namoo.security.exam.mybatis.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// DB에 저장된 사용자 정보를 읽어온다.
		User user = userRepository.readUserByUserId(username);
		if(user == null){
            throw new UsernameNotFoundException("로그인 사용자 정보를 찾을 수 없습니다.");
        }
		
		// 여기서는 예제로 사용자 정보만 DB로 저장하고 있지만, 
		// 사용자에 따른 역할정보도 따로 DB로 관리하여 조회해서 CustomUserDetails의 authorities에 세팅하여 사용할 수 있다.
		// 이 예제에서는 사용자 역할을 임의로 모두 ROLE_USER로 주고, ihchoi에만 따로 ROLE_ADMIN을 추가로 주고 있다.
        CustomUserDetails customUserDetails = new CustomUserDetails(user.getUserId(), user.getUserName(), user.getUserPassword());
        customUserDetails.addAuthority(new SimpleGrantedAuthority("ROLE_USER"));
        if ("ihchoi".equals(user.getUserId())) {
            customUserDetails.addAuthority(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return customUserDetails;
	}

}
