package ebolasafe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ebolasafe.dto.assemblers.UserAssembler;
import ebolasafe.dto.resources.UserResource;
import ebolasafe.models.User;
import ebolasafe.services.UserService;


@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	UserService userServe;
	
	@Autowired
	UserAssembler assembler;
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(method = RequestMethod.GET, value = "")
	public List<UserResource> getAllUsers()
			throws Exception {
		List<User> users = userServe.getAll();
		return assembler.toResources(users);
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/{id}")
	public UserResource getAllUsers(@PathVariable Long id)
			throws Exception {
		
		return assembler.toResource(userServe.getUserById(id));
	}
	
	
}