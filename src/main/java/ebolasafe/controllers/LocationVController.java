package ebolasafe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ebolasafe.exceptions.LocationNotFound;
import ebolasafe.models.Location;
import ebolasafe.repositories.LocationRepository;

@Controller
@RequestMapping("/location")
public class LocationVController {
	@Autowired
	LocationRepository locRepo;

	//Create a Location
	@RequestMapping(value = "/form")
	public String save(Model model){
		Location location = new Location();
		model.addAttribute("location",location);
		return "/Location/create";
	}
	//update a location
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/form")
	public String update(Model model, @PathVariable Long id){
		model.addAttribute("location",locRepo.findOne(id));
		return "/Location/edit";
	}
	//list all location resources
	@RequestMapping(value = "/list")
	public String listLoc(Model model){
		model.addAttribute("location", locRepo.findAll());
		return "/Location/list";
		
	}
	//show location resource
	@RequestMapping(method = RequestMethod.GET, value = "/show/{id}")
	public String showLocation(Model model, @PathVariable Long id){
		Location location = locRepo.findOne(id);
		
		if(location == null){
			throw new LocationNotFound("The Location does not exist!");
		}else
		model.addAttribute("location", location);
		return "Location/show";
	}
	//save location resource	
	@RequestMapping(method = RequestMethod.POST, value = "")
	public String saveLocation(Location location){
		
		locRepo.saveAndFlush(location);
		return "/Location/create";
		
	}
}
