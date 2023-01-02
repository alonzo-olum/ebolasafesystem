package ebolasafe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ebolasafe.models.User;
import ebolasafe.models.UserCreateForm;
import ebolasafe.repositories.UsersRepository;


@Service
public class UserService {

	@Autowired
	UsersRepository repo;

	public List<User> getAll() {
		return repo.findAll();
	}

	public User getUserById(Long id) {
		return repo.findOne(id);
	}
	public User getUserByUsername(String Username){
		return repo.findByUsername(Username);
	}

	public User createUser(UserCreateForm userRes) {
		User user = new User();
		user.setUsername(userRes.getUsername());
		user.setPassword(new BCryptPasswordEncoder().encode(userRes.getPassword()));
		user.setPhone(userRes.getPhone());
		user.setEnabled(true);
		user = repo.saveAndFlush(user);
		return user;
		}

	
	}
