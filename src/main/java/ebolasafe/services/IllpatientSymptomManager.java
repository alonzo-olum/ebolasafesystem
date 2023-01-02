package ebolasafe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebolasafe.dto.resources.IllpatientSymptomsResource;
import ebolasafe.models.IllpatientSymptoms;
import ebolasafe.repositories.IllpatientSymptomRepository;

@Service
public class IllpatientSymptomManager {
	
	@Autowired
	IllpatientSymptomRepository illpatientRepo;
	

	public IllpatientSymptoms createillpatientsymptom(IllpatientSymptomsResource illpatientres){
		IllpatientSymptoms illpatient = new IllpatientSymptoms();
		illpatient.setPhoneNumber(illpatientres.getPhone());
		illpatient.setSymptom(illpatientres.getSymptom());
		illpatientRepo.save(illpatient);
		return illpatient;
	}
	public IllpatientSymptoms updateillpatientSymp(Long id, IllpatientSymptomsResource illpatientRes){
		
		IllpatientSymptoms _illpatient = illpatientRepo.findOne(id);
		_illpatient.setPhoneNumber(illpatientRes.getPhone());
		_illpatient.setSymptom(illpatientRes.getSymptom());
		return illpatientRepo.save(_illpatient);
			
		}
		public List<IllpatientSymptoms> getIllpatientSymptomsReport(String phone) {
			// TODO Auto-generated method stub
			return null;
			
		}
		public List<IllpatientSymptoms> getAllIllpatientSymptomsReport(){
			return this.illpatientRepo.findAll();
		}
		public void deleteSymptomsReport(Long id) {
			// TODO Auto-generated method stub
			this.illpatientRepo.delete(id);
		}
		
		
}
