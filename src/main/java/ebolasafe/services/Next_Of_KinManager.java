package ebolasafe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebolasafe.dto.assemblers.PatientAssembler;
import ebolasafe.dto.resources.NextOfKinResource;
import ebolasafe.models.NextOfKin;
import ebolasafe.repositories.NextOfKinRepo;

@Service
public class Next_Of_KinManager {
	

	NextOfKinResource NextOfKinResource = new NextOfKinResource();
	@Autowired
	NextOfKinRepo nextofkinrepo;
	@Autowired
	PatientAssembler passembler;
	
	
	public List<NextOfKin> getAllKins() {
		return nextofkinrepo.findAll();
	}

	public NextOfKin getKin(String phone) {
		return nextofkinrepo.finderMethod(phone);
	}

	public void deleteKin(Long id) {
		nextofkinrepo.delete(id);
			
	}
		

}
