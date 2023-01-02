package ebolasafe.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ebolasafe.dto.assemblers.TemperatureAssembler;
import ebolasafe.dto.resources.TemperatureResource;
import ebolasafe.exceptions.TemperatureReadingNotFoundException;
import ebolasafe.models.Temperature;
import ebolasafe.repositories.TemperatureRepository;
import ebolasafe.services.TemperatureManager;


@RestController
@RequestMapping("/rest/temperature")
public class TemperatureController {
TemperatureAssembler assembler = new TemperatureAssembler();
	
	@Autowired
	TemperatureManager manager;
	
	@Autowired
	TemperatureRepository repo;
	
	@RequestMapping(method = RequestMethod.GET, value = "")
	@ResponseStatus(HttpStatus.OK)
	public List<TemperatureResource> getAllTemperatureReadings()
			throws Exception {
		List<Temperature> TemperatureReadings = manager.getAllTemperatureReadings();
		return assembler.toResources(TemperatureReadings);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "")
	@ResponseStatus(HttpStatus.CREATED)
	public TemperatureResource createTemperatureReading(
			@RequestBody TemperatureResource _TemperatureReading) throws Exception {
		
		Temperature TemperatureReading = manager.createTemperatureReading(_TemperatureReading);
		System.out.println("check this out...."+TemperatureReading.toString());
		return assembler.toResource(TemperatureReading);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{phone}")
	@ResponseStatus(HttpStatus.OK)
	public List<TemperatureResource> getTemperatureReading(
			@PathVariable("phone") String phone){
		List<Temperature> TemperatureReading = manager.getTemperatureReading(phone);
		if (TemperatureReading == null)
			throw new TemperatureReadingNotFoundException();
		return assembler.toResources(TemperatureReading);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public TemperatureResource updateTemperatureReading(@PathVariable("id") Long id,
			@RequestBody TemperatureResource _TemperatureReading) {
		Temperature TemperatureReading = manager.updateTemperatureReading(id,_TemperatureReading);
		return assembler.toResource(TemperatureReading);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteTemperatureReading(@PathVariable("id") Long id) {
		manager.deleteTemperatureReading(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/{phone}/{date}")
	@ResponseStatus(HttpStatus.OK)
	public List<TemperatureResource> ListReadings(@PathVariable("phone")String phone ,@PathVariable("date") String date){
		
		List<Temperature> Readings = manager.getDatereading(phone, date);
		return assembler.toResources(Readings);
	}

}
