package ebolasafe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ebolasafe.exceptions.UserNotFoundException;
import ebolasafe.models.CurrentUser;
import ebolasafe.models.User;

@Service
public class CurrentUserService implements UserDetailsService {
	UserService userservice;
	
	@Autowired
	public CurrentUserService(UserService userservice) {
		// TODO Auto-generated constructor stub
		this.userservice = userservice;
	}
	@Override
	public CurrentUser loadUserByUsername(String username )
			throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = userservice.getUserByUsername(username);
		if(user != null){
			return new CurrentUser(user);
		}else{
			throw new UsernameNotFoundException(String.format("The user=%s does not exist", username));
		}
		
	}

}
