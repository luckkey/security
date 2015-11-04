package namoo.security.exam.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 로그인시에 필요한 필드들을 추가/삭제하면 된다.
 * 예를 들어 실제 DB에 저장된 사용자의 이름, 로그인 일자 등을 추가할 수 있다.
 * 여기서 추가한 필드들은 getRealUserName()처럼 static 메소드로 만들어서 나중에 JSP나 controller에서 사용할 수 있다.
 */
public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 2304488713406091560L;
	
	private String memberId;
    private String memberName;
    private String memberPassword;
    private List<GrantedAuthority> authorities;
    
    public CustomUserDetails(String userId, String userName, String userPassword) {
        this.memberId = userId;
        this.memberName = userName;
        this.memberPassword = userPassword;
    }
    
    public static CustomUserDetails getLoginUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.getPrincipal() != null) {
			Object result = authentication.getPrincipal();
			if (result instanceof CustomUserDetails) {
				return (CustomUserDetails) result;
			}
		}
		return null;
	}
    
    /**
     * 실제 사용자 이름
     */
    public static String getRealUserName() {
    	CustomUserDetails userDetails = getLoginUser();
    	if (userDetails != null) return userDetails.getMemberName();
    	return null;
	}

    public void addAuthority(GrantedAuthority authority) {
        if (authorities == null) {
            authorities = new ArrayList<GrantedAuthority>();
        }
        authorities.add(authority);
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (authorities == null) authorities = new ArrayList<GrantedAuthority>();
		return authorities;
	}

	@Override
	public String getPassword() {
		return memberPassword;
	}
	
	@Override
	public String getUsername() {
		return memberId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
