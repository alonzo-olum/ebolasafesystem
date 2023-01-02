package ebolasafe.dto.assemblers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import ebolasafe.dto.resources.SymptomsPatientResource;
import ebolasafe.models.SymptomsPatient;
import ebolasafe.restControllers.SymptomsPatientController;

public class SymptomsPatientResourceAssembler extends ResourceAssemblerSupport<SymptomsPatient,SymptomsPatientResource>{

	public SymptomsPatientResourceAssembler() {
		super(SymptomsPatientController.class, SymptomsPatientResource.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public SymptomsPatientResource toResource(SymptomsPatient symptompatient) {
		// TODO Auto-generated method stub
		SymptomsPatientResource sympatientRes = new SymptomsPatientResource();
		sympatientRes.setUsername(symptompatient.getUsername());
		sympatientRes.setSymptom(symptompatient.getSymptom());
		sympatientRes.setPhone(symptompatient.getPhoneNumber());
		sympatientRes.setDate_added(symptompatient.getDate_added());
		sympatientRes.setIdRes(symptompatient.getSpid());
		return sympatientRes;
	}
	public List<SymptomsPatientResource> toResources(List<SymptomsPatient> symptomsPat)
	{
		
		List<SymptomsPatientResource> symptRes = new ArrayList<SymptomsPatientResource>();
			for(SymptomsPatient sympts : symptomsPat){
				symptRes.add(toResource(sympts));
			}
		return symptRes;
	}

}
