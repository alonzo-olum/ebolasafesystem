package ebolasafe.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ebolasafe.dto.assemblers.PatientAssembler;
import ebolasafe.dto.resources.PatientResource;
import ebolasafe.exceptions.PatientNotFoundException;
import ebolasafe.models.Patient;
import ebolasafe.repositories.PatientsRepository;
import ebolasafe.services.PatientsManager;

@RestController
@RequestMapping("/rest/patients")
public class PatientsController {
	PatientAssembler assembler = new PatientAssembler();
	
	@Autowired
	PatientsManager manager;
	
	@Autowired
	PatientsRepository repo;
	@RequestMapping(method = RequestMethod.GET, value = "")
	@ResponseStatus(HttpStatus.OK)
	public List<PatientResource> getAllPatients()
			throws Exception {
		List<Patient> patients = manager.getAllPatients();
		return assembler.toResources(patients);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "")
	@ResponseStatus(HttpStatus.CREATED)
	public PatientResource createPatient(
			@RequestBody PatientResource _patient) throws Exception {

		System.out.println("###############"+_patient.toString());
		Patient patient = manager.createPatient(_patient);
		return assembler.toResource(patient);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{phone}")
	@ResponseStatus(HttpStatus.OK)
	public PatientResource getPatient(
			@PathVariable("phone") String phone) throws Exception{
		Patient patient = manager.getPatient(phone);
		if (patient == null)
			throw new PatientNotFoundException(phone);
		return assembler.toResource(patient);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{phone}")
	@ResponseStatus(HttpStatus.OK)
	public PatientResource updatePatient(@PathVariable("phone") String phone,
			@RequestBody PatientResource _patient) {
		Patient patient = manager.updatePatient(phone,_patient);
		System.out.println("check it out:" +phone);
		return assembler.toResource(patient);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePatient(@PathVariable("id") Long id) {
		manager.deletePatient(id);
	
	}
}
