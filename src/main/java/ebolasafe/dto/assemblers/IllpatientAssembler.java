package ebolasafe.dto.assemblers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.tools.generic.LinkTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import ebolasafe.dto.resources.Illpatientresource;
import ebolasafe.dto.resources.LocationResource;
import ebolasafe.models.Illpatient;
import ebolasafe.models.Location;
import ebolasafe.repositories.SymptomsRepository;
import ebolasafe.restControllers.IllpatientController;
import ebolasafe.restControllers.LocationController;
import ebolasafe.util.ExtendedLink;
@Service
public class IllpatientAssembler extends ResourceAssemblerSupport<Illpatient, Illpatientresource>{

	public IllpatientAssembler() {
		super(IllpatientController.class, Illpatientresource.class);
		// TODO Auto-generated constructor stub
	}
	@Autowired
	SymptomsRepository symptomRepo;
	
	@Autowired
	SymptomsAssembler symptom_assembler;
	@Autowired
	LocationAssembler loc_assembler;
	@Override
	public Illpatientresource toResource(Illpatient ill) {
		// TODO Auto-generated method stub
		Illpatientresource illRes = new Illpatientresource();
		illRes.setIdRes(ill.getId());
		illRes.setName(ill.getName());
		illRes.setContact_mode(ill.getContact_mode());
		illRes.setPhone(ill.getPhoneNumber());
		illRes.setRegistration_day(ill.getRegistrationDate());
		illRes.setStatus(ill.getStatus());
		illRes.setRelation(ill.getRelationship());
		illRes.add(new ExtendedLink(linkTo(methodOn(LocationController.class).getLocationByID(ill.getLocation().getId())).toString(), "LOCATION", "GET"));
/*		illRes.setLocationRes(loc_assembler.toResource(ill.getLocation()));*/
			
		return illRes;
	}

	public List<Illpatientresource> toResources(
			List<Illpatient> ill) {
		// TODO Auto-generated method stub
		List<Illpatientresource> illRes = new ArrayList<Illpatientresource>();
		for(Illpatient i: ill){
			illRes.add(toResource(i));
		}
		return illRes;
	}

	public Location toLocationEntity(LocationResource locRes){
		Location location = new Location();
		location.setCountry(locRes.getCountry());
		location.setProvince(locRes.getProvince());
		location.setTown(locRes.getTown());
		return location;
	}
	
}
