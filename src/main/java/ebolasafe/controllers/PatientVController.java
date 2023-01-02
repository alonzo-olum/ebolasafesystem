package ebolasafe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ebolasafe.models.Patient;
import ebolasafe.repositories.PatientsRepository;

@Controller
@RequestMapping("/patient")
public class PatientVController {
	@Autowired
	PatientsRepository patientRepo;
	
	//Create a patient
	@RequestMapping("/form")
	public String create(Model model) {
		Patient patient = new Patient();
		model.addAttribute(patient);
		return "/Patients/create";

	}
	
	//save patient resource	
		@RequestMapping(method = RequestMethod.POST, value = "")
		public String savePatient(Patient patient){
			
			patientRepo.saveAndFlush(patient);
			return "/Patients/create";
			
		}
}
