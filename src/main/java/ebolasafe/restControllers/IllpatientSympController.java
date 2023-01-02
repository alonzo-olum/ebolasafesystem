package ebolasafe.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ebolasafe.dto.assemblers.IllpatientSymptomAssembler;
import ebolasafe.dto.resources.IllpatientSymptomsResource;
import ebolasafe.models.IllpatientSymptoms;
import ebolasafe.services.IllpatientSymptomManager;


@RestController
@RequestMapping("/rest/illpatient/symptoms/")
public class IllpatientSympController {
	
	IllpatientSymptomAssembler illpatientAssembler = new IllpatientSymptomAssembler();
	@Autowired
	IllpatientSymptomManager illpatientManger;
	
	@RequestMapping(method = RequestMethod.POST, value = "")
	@ResponseStatus(HttpStatus.CREATED)
	public IllpatientSymptomsResource createIllpatientsymp(@RequestBody IllpatientSymptomsResource illpatientres){
		IllpatientSymptoms illpatient = illpatientManger.createillpatientsymptom(illpatientres);
		return illpatientAssembler.toResource(illpatient);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="")
	@ResponseStatus(HttpStatus.OK)
	public List<IllpatientSymptomsResource> getIllpatientsymp(){
		List<IllpatientSymptoms> illpatientres = illpatientManger.getAllIllpatientSymptomsReport();
		return illpatientAssembler.toResources(illpatientres);
	}
}
