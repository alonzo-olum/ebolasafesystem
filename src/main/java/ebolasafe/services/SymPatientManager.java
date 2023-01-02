package ebolasafe.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebolasafe.dto.resources.SymptomsPatientResource;
import ebolasafe.models.SymptomsPatient;
import ebolasafe.repositories.SymptomPatientRepository;
import ebolasafe.util.DataTypeConverter;

@Service
public class SymPatientManager {
	
		
	@Autowired
	SymptomPatientRepository symptPatRepo;
		
	public SymptomsPatient createSympPatient(SymptomsPatientResource spRes){
		
		SymptomsPatient sp = new SymptomsPatient();
		sp.setUsername(spRes.getUsername());
		sp.setSymptom(spRes.getSymptom());
		sp.setPhoneNumber(spRes.getPhone());
		sp.setDate_added(spRes.getDate_added());
		return symptPatRepo.save(sp);
		
	}
	
	public SymptomsPatient updateSympatient(Long id, SymptomsPatientResource spRes){
		
	SymptomsPatient _sp = symptPatRepo.findOne(id);
	_sp.setDate_added(spRes.getDate_added());
	_sp.setPhoneNumber(spRes.getPhone());
	_sp.setSymptom(spRes.getSymptom());
	_sp.setUsername(spRes.getUsername());
	return symptPatRepo.save(_sp);
		
	}
	public List<SymptomsPatient> getSymptomsReport(String phone) {
		// TODO Auto-generated method stub
		return this.symptPatRepo.finderMethod(phone);
		
	}
	public List<SymptomsPatient> getAllSymptomsReport(){
		return this.symptPatRepo.findAll();
	}
	public void deleteSymptomsReport(Long id) {
		// TODO Auto-generated method stub
		this.symptPatRepo.delete(id);
	}
	
	public List<SymptomsPatient> getdateReadings(String phone, String date){
		
		Date _date = DataTypeConverter.getDate(date);
		return this.symptPatRepo.dateFinder(phone, _date);
	}
}
