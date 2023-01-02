package ebolasafe.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebolasafe.dto.resources.SavedUserResource;
import ebolasafe.exceptions.PasswordMismatchException;
import ebolasafe.exceptions.UserNotFoundException;
import ebolasafe.models.Patient;
import ebolasafe.models.SavedUser;
import ebolasafe.repositories.PatientsRepository;
import ebolasafe.repositories.SavedUserRepository;

@Service
public class LoginService {
	
	@Autowired
	PatientsRepository patientRepo;
	
	@Autowired
	SavedUserRepository savedRepo;
	
	static final Logger LOG = Logger.getLogger(LoginService.class.getName());
	
	public Patient authenticateLogin(String phone,
			String password) {
		// TODO Auto-generated method stub
		
		Patient _su = patientRepo.finderMethod(phone);
		
		if( _su != null){
			LOG.info(_su.getUsername());
			if(!(_su.getPassword()).equals(password)){
				
				throw new PasswordMismatchException("Wrong user password!");
			}
			
		}else{
			
			throw new UserNotFoundException(String.format("The phone %s does not exist!", phone ));
		}
		return _su;
	}
	
	public SavedUser saveLoggedUser(SavedUserResource saveduseRes) {
		// TODO Auto-generated method stub
		SavedUser su = new SavedUser();
		su.setDate_logged(saveduseRes.getDate_logged());
		su.setPassword(saveduseRes.getPassword());
		su.setPhone(saveduseRes.getPhone());
		//su.setUsername(saveduseRes.getUsername());
		su.setReg_status(saveduseRes.getStatus());
		return this.savedRepo.save(su);
	}
	
	public void deleteLoggedUser(Long id){
		this.savedRepo.delete(id);
		
	}

}
