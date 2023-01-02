package ebolasafe.dto.assemblers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import ebolasafe.dto.resources.NextOfKinResource;
import ebolasafe.models.NextOfKin;
import ebolasafe.repositories.LocationRepository;
import ebolasafe.restControllers.PatientsController;

@Service
public class NextOfKinAssembler extends ResourceAssemblerSupport<NextOfKin,NextOfKinResource>{

	@Autowired
	LocationAssembler locAssembler;
	@Autowired
	LocationRepository locRepo;
	
	public NextOfKinAssembler() {
		super(PatientsController.class, NextOfKinResource.class);
	}

	@Override
	public NextOfKinResource toResource(NextOfKin nextOfKin) {
		NextOfKinResource nextOfkinRes = new NextOfKinResource();
		nextOfkinRes.setName(nextOfKin.getName());
		nextOfkinRes.setIdRes(nextOfKin.getId());
		nextOfkinRes.setPhoneNumber(nextOfKin.getPhoneNumber());
		nextOfkinRes.setLocationRes(locAssembler.toResource(nextOfKin.getLocation()));;
		nextOfkinRes.setRelation(nextOfKin.getRelation());
		return nextOfkinRes;
	}
	
	public List<NextOfKinResource> toResources(List<NextOfKin> all) {
		List<NextOfKinResource> nextOfKinRes = new ArrayList<NextOfKinResource>();
		for (NextOfKin nextOfKin : all) {
			nextOfKinRes.add(toResource(nextOfKin));
		}
		return nextOfKinRes;
	}
	
	
}
