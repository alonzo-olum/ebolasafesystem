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

import ebolasafe.dto.assemblers.LocationAssembler;
import ebolasafe.dto.resources.LocationResource;
import ebolasafe.models.Location;
import ebolasafe.repositories.LocationRepository;
import ebolasafe.services.LocationManager;

@RestController
@RequestMapping("/rest/location")
public class LocationController {
	
	LocationAssembler _locAssembler = new LocationAssembler();
	
	@Autowired
	LocationRepository locationRepository;
	@Autowired
	LocationManager locManager;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "")
	@ResponseStatus(HttpStatus.CREATED)
	public LocationResource createLocation(@RequestBody LocationResource locRes) throws Exception{
		Location location = locManager.createLocation(locRes);
			return 	_locAssembler.toResource(location);
		
	}
	@RequestMapping(method = RequestMethod.GET, value = "")
	@ResponseStatus(HttpStatus.OK)
	public List<LocationResource> getLocation(){
		List<Location> location = locManager.getAllLocation();
		
		return _locAssembler.toResources(location);
		
		
	}
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public LocationResource getLocationByID(@PathVariable Long id){
		Location location = locManager.getLocationById(id);
		
		return _locAssembler.toResource(location);
		
		
	}
	@RequestMapping(method = RequestMethod.GET, value = "/town/{town}")
	@ResponseStatus(HttpStatus.OK)
	public List<LocationResource> _getLocationAstown(@PathVariable String town){
		
		List<Location> location = locManager.getLocationAstown(town);
		return _locAssembler.toResources(location);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/country/{country}")
	@ResponseStatus(HttpStatus.OK)
	public List<LocationResource> _getLocation(@PathVariable String country){
		
		List<Location> location = locManager.getLocation(country);
		return _locAssembler.toResources(location);
	}
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public LocationResource updateLocation(@PathVariable Long id, @RequestBody LocationResource locRes){
		Location loc = locManager.updateLocation(id, locRes);
		return _locAssembler.toResource(loc);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteLocation(@PathVariable("id") Long id){
		locManager.deleteLocation(id);
	}
}
