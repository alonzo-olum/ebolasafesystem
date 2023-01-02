package ebolasafe.dto.assemblers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import ebolasafe.dto.resources.IllpatientSymptomsResource;
import ebolasafe.models.IllpatientSymptoms;
import ebolasafe.restControllers.IllpatientSympController;

public class IllpatientSymptomAssembler extends ResourceAssemblerSupport<IllpatientSymptoms	, IllpatientSymptomsResource>{

	public IllpatientSymptomAssembler() {
		super(IllpatientSympController.class, IllpatientSymptomsResource.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IllpatientSymptomsResource toResource(IllpatientSymptoms illpatient) {
		// TODO Auto-generated method stub
		IllpatientSymptomsResource illpatientRes = new IllpatientSymptomsResource();
		illpatientRes.setPhone(illpatient.getPhoneNumber());
		illpatientRes.setSymptom(illpatient.getSymptom());
		return illpatientRes;
	}
	
	public List<IllpatientSymptomsResource> toResources(List<IllpatientSymptoms> illpatients){
		List<IllpatientSymptomsResource> illpatientres = new ArrayList<IllpatientSymptomsResource>();
		for(IllpatientSymptoms illpat : illpatients ){
			illpatientres.add(toResource(illpat));
		}
		return illpatientres;
	}

}
