package ebolasafe.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ebolasafe.dto.assemblers.SymptomsAssembler;
import ebolasafe.dto.resources.SymptomsResource;
import ebolasafe.exceptions.SymptomsReportNotFoundException;
import ebolasafe.models.Symptoms;
import ebolasafe.repositories.SymptomsRepository;
import ebolasafe.services.SymptomsManager;


@RestController
@RequestMapping("/rest/symptoms")
public class SymptomsController {
	SymptomsAssembler assembler = new SymptomsAssembler();
	
	@Autowired
	SymptomsManager manager;
	
	@Autowired
	SymptomsRepository repo;
	
		
	@RequestMapping(method = RequestMethod.GET, value = "")
	@ResponseStatus(HttpStatus.OK)
	public List<SymptomsResource> getAllSymptomsReports()
			throws Exception {
		List<Symptoms> SymptomsReports = manager.getAllSymptomsReports();
		return assembler.toResources(SymptomsReports);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "")
	@ResponseStatus(HttpStatus.CREATED)
	public SymptomsResource createSymptomsReport(
			@RequestBody SymptomsResource _Symptoms) throws Exception {
		Symptoms Symptoms = manager.createSymptomsReport(_Symptoms);
		System.out.println(Symptoms.toString());
		return assembler.toResource(Symptoms);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{username}")
	@ResponseStatus(HttpStatus.OK)
	public List<SymptomsResource> getSymptomsReport(
			@PathVariable("username") String username){
		List<Symptoms> symptoms = manager.getSymptomsReport(username);
		if (symptoms == null)
			throw new SymptomsReportNotFoundException(String.format("This username %s has no symptoms", username));
		return assembler.toResources(symptoms);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public SymptomsResource updateSymptomsReport(@PathVariable("id") Long id,
			@RequestBody SymptomsResource _SymptomsReport) {
		Symptoms SymptomsReport = manager.updateSymptomsReport(id,_SymptomsReport);
		return assembler.toResource(SymptomsReport);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteSymptomsReport(@PathVariable("id") Long id) {
		manager.deleteSymptomsReport(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
