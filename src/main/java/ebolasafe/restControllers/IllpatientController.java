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

import ebolasafe.dto.assemblers.IllpatientAssembler;
import ebolasafe.dto.resources.Illpatientresource;
import ebolasafe.models.Illpatient;
import ebolasafe.services.IllpatientManager;

@RestController
@RequestMapping("/rest/illpatient")
public class IllpatientController {
	
	IllpatientAssembler illAssembler = new IllpatientAssembler();
	@Autowired
	IllpatientManager illManager;
	
	@RequestMapping(method = RequestMethod.POST, value="")
	@ResponseStatus(HttpStatus.CREATED)
	public Illpatientresource createIllpatient(@RequestBody Illpatientresource illRes){
		Illpatient ill = illManager.createIllpatient(illRes);
		System.out.println("XXXXXXXXXXXXXX"+illRes.toString());
		return illAssembler.toResource(ill);
		
	}
	@RequestMapping(method = RequestMethod.GET, value = "")
	@ResponseStatus(HttpStatus.OK)
	public List<Illpatientresource> getIllpatients(){
		List<Illpatient> ills = illManager.getIllpatients();
		return illAssembler.toResources(ills);
		
	}
	@RequestMapping(method = RequestMethod.GET, value = "/{phone}")
	@ResponseStatus(HttpStatus.OK)
	public Illpatientresource getIllpatient(@PathVariable String phone){
		Illpatient ill = illManager.getIllpatient(phone);
		return illAssembler.toResource(ill);
	}
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteIllpatient(@PathVariable("id") Long id){
		illManager.deleteIllpatient(id);
		}
}
