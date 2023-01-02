package ebolasafe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebolasafe.dto.assemblers.PatientAssembler;
import ebolasafe.dto.resources.NextOfKinResource;
import ebolasafe.dto.resources.PatientResource;
import ebolasafe.exceptions.UserAlreadyExistingException;
import ebolasafe.models.Patient;
import ebolasafe.repositories.PatientsRepository;

@Service
public class PatientsManager {
	
	

	NextOfKinResource NextOfKinResource = new NextOfKinResource();
	@Autowired
	PatientsRepository patientsRepository;
	@Autowired
	PatientAssembler passembler;
	
	
	
	public List<Patient> getAllPatients() {
		return patientsRepository.findAll();
	}

	public Patient getPatient(String phone) {
		return patientsRepository.finderMethod(phone);
	}

	public void deletePatient(Long id) {
		patientsRepository.delete(id);
			
	}

	public Patient updatePatient(String phone, PatientResource patientRes) {		
		Patient patient = patientsRepository.finderMethod(phone);
		patient.setUsername(patientRes.getName());
		patient.setEmail(patientRes.getEmail());
		patient.setPassword(patientRes.getPassword());
		patient.setPhoneNumber(patientRes.getPhoneNumber());
		patient.setRegistrationDate(patientRes.getRegistrationDate());
		patient.setReg_status(patientRes.getReg_status());
		patient.setLocation(passembler.toLocationEntity(patientRes.getLocRes()));
		patient.setNextOfKin(passembler.tonextOfKinEntity(patientRes.getNextOfKinRes()));
		this.patientsRepository.save(patient);
		return patient;
	}

	public Patient createPatient(PatientResource _patient) {
		
		String phone = _patient.getPhoneNumber();
		Patient patients = patientsRepository.finderMethod(phone);
		if(patients != null){
			throw new UserAlreadyExistingException("Please select another phone number, there already exists a user with this one!");
		}else{
			Patient patient = new Patient();
			patient.setEmail(_patient.getEmail());
			patient.setUsername(_patient.getUserName());
			patient.setName(_patient.getName());
			patient.setPassword(_patient.getPassword());
			patient.setPhoneNumber(_patient.getPhoneNumber());
			patient.setReg_status(_patient.getReg_status());
			patient.setRegistrationDate(_patient.getRegistrationDate());
			patient.setLocation(passembler.toLocationEntity(_patient.getLocRes()));
			patient.setNextOfKin(passembler.tonextOfKinEntity(_patient.getNextOfKinRes()));
			this.patientsRepository.save(patient);
			return patient;
			
		}
		
	}
		
	
}
