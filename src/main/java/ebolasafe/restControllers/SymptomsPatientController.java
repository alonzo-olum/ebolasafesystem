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

import ebolasafe.dto.assemblers.SymptomsPatientResourceAssembler;
import ebolasafe.dto.resources.SymptomsPatientResource;
import ebolasafe.exceptions.SymptomsReportNotFoundException;
import ebolasafe.models.SymptomsPatient;
import ebolasafe.services.SymPatientManager;

@RestController
@RequestMapping("/rest/symptoms/symp_patient")
public class SymptomsPatientController {
	
	SymptomsPatientResourceAssembler sympPatAss = new SymptomsPatientResourceAssembler();
	
	@Autowired
	SymPatientManager symPman;
		
	@RequestMapping(method = RequestMethod.POST, value = "")
	@ResponseStatus(HttpStatus.CREATED)
	public SymptomsPatientResource createSymp_patient(
			@RequestBody SymptomsPatientResource spRes) throws SymptomsReportNotFoundException{
		SymptomsPatient sp = symPman.createSympPatient(spRes);
		System.out.println("##############" + sp.toString());
		return this.sympPatAss.toResource(sp);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/{phone}")
	@ResponseStatus(HttpStatus.OK)
	public List<SymptomsPatientResource> getSymptomsReport(
			@PathVariable("phone") String phone){
		List<SymptomsPatient> symptomsReport = symPman.getSymptomsReport(phone);
		if (symptomsReport == null)
			throw new SymptomsReportNotFoundException(String.format("This username having the %s number has no symptoms", phone));
		return sympPatAss.toResources(symptomsReport);
	}
	@RequestMapping(method = RequestMethod.GET, value = "")
	@ResponseStatus(HttpStatus.OK)
	public List<SymptomsPatientResource> getAllSymptomsReport(){
		List<SymptomsPatient> symptomspatient = symPman.getAllSymptomsReport();
		return sympPatAss.toResources(symptomspatient);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> deleteSymptomsReport(@PathVariable("id") Long id) {
		symPman.deleteSymptomsReport(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/{phone}/{date}")
	@ResponseStatus(HttpStatus.OK)
	public List<SymptomsPatientResource> getDateSymptoms(@PathVariable("phone")String phone,@PathVariable("date")String date){
		List<SymptomsPatient> sympPatient = symPman.getdateReadings(phone, date);
		return sympPatAss.toResources(sympPatient);
		
	}
}
