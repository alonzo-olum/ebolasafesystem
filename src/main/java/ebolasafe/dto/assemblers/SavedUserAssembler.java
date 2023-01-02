package ebolasafe.dto.assemblers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import ebolasafe.controllers.UserLoginController;
import ebolasafe.dto.resources.SavedUserResource;
import ebolasafe.models.SavedUser;

@Service
public class SavedUserAssembler extends ResourceAssemblerSupport<SavedUser, SavedUserResource> {

	
	
	public SavedUserAssembler() {
		super(UserLoginController.class, SavedUserResource.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public SavedUserResource toResource(SavedUser su) {
		// TODO Auto-generated method stub
		SavedUserResource saveduseres = new SavedUserResource();
		//saveduseres.setUsername(su.getUsername());
		saveduseres.setPhone(su.getPhone());
		saveduseres.setPassword(su.getPassword());
		saveduseres.setDate_logged(su.getDate_logged());
		saveduseres.setStatus(su.getReg_status());
		return saveduseres;
	}
	
	public List<SavedUserResource> toResource(List<SavedUser> allsu){
		List<SavedUserResource> savedUseRes = new ArrayList<SavedUserResource>();
		for (SavedUser _su : allsu) {
			savedUseRes.add(toResource(_su));
		}
		return savedUseRes;
	}
}
