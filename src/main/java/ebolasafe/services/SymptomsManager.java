package ebolasafe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebolasafe.dto.resources.SymptomsResource;
import ebolasafe.models.Symptoms;
import ebolasafe.repositories.SymptomsRepository;

@Service
public class SymptomsManager {
	
	@Autowired
	SymptomsRepository symptomsRepo;


	public List<Symptoms> getAllSymptomsReports() {
		// TODO Auto-generated method s

		return symptomsRepo.findAll();
	}

	public Symptoms createSymptomsReport(
			SymptomsResource _Symptoms) {
		// TODO Auto-generated method stub
		Symptoms symptoms = new Symptoms();
		symptoms.setDate_added(_Symptoms.getDate_added());
		symptoms.setSymptom(_Symptoms.getSymptom());
		return this.symptomsRepo.save(symptoms);
	}

	public List<Symptoms> getSymptomsReport(String username) {
		// TODO Auto-generated method stub
		return this.symptomsRepo.finderMethod(username);
		
	}

	public Symptoms updateSymptomsReport(Long id,
			SymptomsResource _Symptoms) {
		// TODO Auto-generated method stub
		Symptoms symptoms = symptomsRepo.findOne(id);
		symptoms.setSymptom(_Symptoms.getSymptom());
		symptoms.setDate_added(_Symptoms.getDate_added());
		return symptoms;
	}

	public void deleteSymptomsReport(Long id) {
		// TODO Auto-generated method stub
		this.symptomsRepo.delete(id);
	}

}
