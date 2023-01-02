package ebolasafe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ebolasafe.exceptions.SymptomsReportNotFoundException;
import ebolasafe.models.Symptoms;
import ebolasafe.repositories.SymptomsRepository;


@Controller
@RequestMapping("/symptoms")
public class SymptomsVController {
	@Autowired
	SymptomsRepository sympRepo;
	
	//List all symptoms
	@RequestMapping(value = "/list")
	public String list(Model model){
		model.addAttribute("symptoms", sympRepo.findAll());
		return "Symptoms/list";
	}
	
	//Create new symptom
	@RequestMapping( value = "/form")
	public String create(Model model){
		Symptoms symptoms = new Symptoms();
		model.addAttribute("symptoms", symptoms);
		return "Symptoms/create";
		
	}
	//update a symptom
	@RequestMapping(method = RequestMethod.GET, value = "/{Id}/form")
	public String update(Model model, @PathVariable Long Id){
		model.addAttribute("symptoms", sympRepo.findOne(Id));
		return "Symptoms/edit";
	}
	
	//show a particular symptom
	@RequestMapping(method = RequestMethod.GET, value = "/show/{Id}")
	public String show(Model model, @PathVariable Long Id){
		
		Symptoms symptoms = sympRepo.findOne(Id);
		
		if(symptoms == null){
			throw new SymptomsReportNotFoundException();
		}
		
		model.addAttribute("symptoms", symptoms);
		return "Symptoms/show";
	}
	//save the symptom resoure
	@RequestMapping(method = RequestMethod.POST, value = "")
	public String save(Symptoms symptoms){
		sympRepo.saveAndFlush(symptoms);
		return "Symptoms/create";
	}

}
