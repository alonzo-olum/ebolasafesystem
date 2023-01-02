package ebolasafe.restControllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ebolasafe.dto.assemblers.NextOfKinAssembler;
import ebolasafe.dto.resources.NextOfKinResource;
import ebolasafe.models.NextOfKin;
import ebolasafe.repositories.NextOfKinRepo;
import ebolasafe.services.Next_Of_KinManager;


@RestController
@RequestMapping("/rest/nok")
public class NextOfKinController {
	
	@Autowired
	NextOfKinRepo nextofkinrepo;
	@Autowired
	Next_Of_KinManager nextofkinMan;
	
	NextOfKinAssembler nextAssembler  = new NextOfKinAssembler();
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteKin(Long id){
		
	 nextofkinMan.deleteKin(id);
	}
	@RequestMapping(method = RequestMethod.GET, value = "")
	public List<NextOfKinResource> getKins(){
		
		List<NextOfKin> nok = nextofkinMan.getAllKins();
		return nextAssembler.toResources(nok);
		
	}
	@RequestMapping(method = RequestMethod.GET, value = "/{phone}")
	public NextOfKinResource getKin(@PathVariable("phone") String phone){
		
		NextOfKin nok = nextofkinMan.getKin(phone);
		return nextAssembler.toResource(nok);
		
	}
	

}
