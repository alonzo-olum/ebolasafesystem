package ebolasafe.dto.assemblers;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import ebolasafe.controllers.UsersController;
import ebolasafe.dto.resources.UserResource;
import ebolasafe.models.User;

@Service
public class UserAssembler extends ResourceAssemblerSupport<User, UserResource> {

	public UserAssembler() {
		super(UsersController.class, UserResource.class);
	}

	@Override
	public UserResource toResource(User user) {
		UserResource userRes = createResourceWithId(user.getUsername(), user);
		userRes.setUsername(user.getUsername());
		userRes.setEnabled(user.isEnabled());
		return userRes;
	}
}