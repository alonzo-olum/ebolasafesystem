package ebolasafe.models;

import org.springframework.security.core.authority.AuthorityUtils;

import ebolasafe.models.User.Role;

public class CurrentUser extends  org.springframework.security.core.userdetails.User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public User user;
	public CurrentUser(User user) {
		super(user.getPhone(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
		// TODO Auto-generated constructor stub
	}
	public User getUser() {
		return user;
	}
	public Long getId(){
		return user.getId();
	}
	public Role getRole(){
		return user.getRole();
	}
	
}
