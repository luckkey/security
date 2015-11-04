package namoo.security.exam.domain.user;

import java.io.Serializable;

public class UserAccountBDT implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5914952899125776195L;

	/** */
	private UserBDT user;
	/** */
	private AccountBDT account;

	public UserBDT getUser() {
		return user;
	}
	public void setUser(UserBDT user) {
		this.user = user;
	}
	public AccountBDT getAccount() {
		return account;
	}
	public void setAccount(AccountBDT account) {
		this.account = account;
	}
}
