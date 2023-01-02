package ebolasafe.controllers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ebolasafe.dto.assemblers.SavedUserAssembler;
import ebolasafe.dto.resources.MessageResource;
import ebolasafe.dto.resources.SavedUserResource;
import ebolasafe.models.Patient;
import ebolasafe.models.SavedUser;
import ebolasafe.services.LoginService;
import ebolasafe.services.MessageManager;


@RestController
@RequestMapping("/rest/patient/login")
public class UserLoginController {
	static final Logger LOG = Logger.getLogger(LoginService.class.getName());
	SavedUserAssembler savedUseRes = new SavedUserAssembler();

	MessageResource msgRes = new MessageResource();
		
	@Autowired
	MessageManager msgservice;
	@Autowired
	LoginService loginservice;
			
	@RequestMapping(method = RequestMethod.POST, value = "")
	@ResponseStatus(HttpStatus.CREATED)
	public SavedUserResource createLog(@RequestBody SavedUserResource suRes){
		
		
		String phone = suRes.getPhone();
		String password = suRes.getPassword();
		Patient _patient =	loginservice.authenticateLogin(phone, password);
		String username = _patient.getUsername();
		LOG.info("The selected patient...password...."+ username + " " +password);
		//set the message notification and save
		msgRes.setUsername(username);
		msgRes.setDate_created(suRes.getDate_logged());
		msgRes.setReg_status(suRes.getStatus());
		msgRes.setPhone(phone);
		this.msgservice.createMessages(msgRes);
		//saving the logged in user for the next accountability
		SavedUser _su = this.loginservice.saveLoggedUser(suRes);
		LOG.info("The saved user...."+_su.toString());
			
		return savedUseRes.toResource(_su);
		 
		 
	}
	@RequestMapping(method = RequestMethod.DELETE, value = "/{Id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteLog(@PathVariable Long Id){
		loginservice.deleteLoggedUser(Id);
	}
	
	
	

}
