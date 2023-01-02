package ebolasafe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebolasafe.dto.assemblers.IllpatientAssembler;
import ebolasafe.dto.resources.Illpatientresource;
import ebolasafe.models.Illpatient;
import ebolasafe.repositories.IllpatientRepository;

@Service
public class IllpatientManager {
	
	
	
	@Autowired
	IllpatientAssembler illAssembler;
	@Autowired
	IllpatientRepository illRepository;
	
	public Illpatient createIllpatient(Illpatientresource illRes){
		
		Illpatient illPatient = new Illpatient();
		
		illPatient.setName(illRes.getName());
		illPatient.setUsername(illRes.getUsername());
		illPatient.setContact_mode(illRes.getContact_mode());
		illPatient.setStatus(illRes.getStatus());
		illPatient.setPhoneNumber(illRes.getPhone());
		illPatient.setRegistrationDate(illRes.getRegistration_day());
		illPatient.setRelationship(illRes.getRelation());
		illPatient.setLocation(illAssembler.toLocationEntity(illRes.getLocationRes()));		
		return illRepository.save(illPatient);
	}
	
	public Illpatient getIllpatient(String phone){
		return illRepository.finderMethod(phone);
	}
	
	public List<Illpatient> getIllpatients(){
		
		return illRepository.findAll();
	}
	public void deleteIllpatient(Long id){
		illRepository.delete(id);
	}
}
