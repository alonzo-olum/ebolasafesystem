package ebolasafe.dto.assemblers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import ebolasafe.dto.resources.TemperatureResource;
import ebolasafe.models.Temperature;
import ebolasafe.restControllers.TemperatureController;

@Service
public class TemperatureAssembler extends ResourceAssemblerSupport <Temperature,TemperatureResource>{

	public TemperatureAssembler() {
		super(TemperatureController.class, TemperatureResource.class);
	}

	@Override
	public TemperatureResource toResource(Temperature tempreading) {
		TemperatureResource tempRes = new TemperatureResource();
		tempRes.setTempId(tempreading.getId());
		tempRes.setTemp(tempreading.getTemp());
		tempRes.setUsername(tempreading.getUsername());
		tempRes.setPhone(tempreading.getPhone());
		tempRes.setDate_taken(tempreading.getDate_taken());
		tempRes.set_timeRes(tempreading.get_time());
		return tempRes;
		
	}
	
	public List<TemperatureResource> toResources(List<Temperature> all) {
		List<TemperatureResource> tempres = new ArrayList<TemperatureResource>();
		for (Temperature tempreading : all) {
			tempres.add(toResource(tempreading));
		}
		return tempres;
	}
}
