package ebolasafe.dto.assemblers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import ebolasafe.dto.resources.LocationResource;
import ebolasafe.models.Location;
import ebolasafe.restControllers.LocationController;

@Service
public class LocationAssembler extends ResourceAssemblerSupport<Location, LocationResource>{

	public LocationAssembler() {
		super(LocationController.class, LocationResource.class);
	}
	

	@Override
	public LocationResource toResource(Location location) {
		LocationResource locRes = new LocationResource();
		locRes.setIdRes(location.getId());
		locRes.setCountry(location.getCountry());
		locRes.setProvince(location.getProvince());
		locRes.setTown(location.getTown());
		return locRes;
	}
	
	public List<LocationResource> toResources(List<Location> all) {
		List<LocationResource> locRes = new ArrayList<LocationResource>();
		for (Location location : all) {
			locRes.add(toResource(location));
		}
		return locRes;
	}
	
	
}
