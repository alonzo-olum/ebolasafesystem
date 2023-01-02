package ebolasafe.dto.assemblers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import ebolasafe.dto.resources.SymptomsResource;
import ebolasafe.models.Symptoms;
import ebolasafe.restControllers.SymptomsController;

@Service
public class SymptomsAssembler extends ResourceAssemblerSupport<Symptoms, SymptomsResource> {

	public SymptomsAssembler() {
		super(SymptomsController.class, SymptomsResource.class);
	}
		
	@Override
	public SymptomsResource toResource(Symptoms symptoms) {
		SymptomsResource symptRes = new SymptomsResource();
		symptRes.setDate_added(symptoms.getDate_added());
		symptRes.setSymptom(symptoms.getSymptom());
		return symptRes;
		}
	
	public List<SymptomsResource> toResources(List<Symptoms> symptoms)
	{
		
		List<SymptomsResource> symptRes = new ArrayList<SymptomsResource>();
			for(Symptoms sympts : symptoms){
				symptRes.add(toResource(sympts));
			}
		return symptRes;
	}
}

