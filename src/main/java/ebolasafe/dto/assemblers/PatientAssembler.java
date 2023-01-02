package ebolasafe.dto.assemblers;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import ebolasafe.dto.resources.LocationResource;
import ebolasafe.dto.resources.NextOfKinResource;
import ebolasafe.dto.resources.PatientResource;
import ebolasafe.models.Location;
import ebolasafe.models.NextOfKin;
import ebolasafe.models.Patient;
import ebolasafe.repositories.LocationRepository;
import ebolasafe.repositories.NextOfKinRepo;
import ebolasafe.restControllers.LocationController;
import ebolasafe.restControllers.NextOfKinController;
import ebolasafe.restControllers.PatientsController;
import ebolasafe.util.ExtendedLink;

@Service
public class PatientAssembler extends ResourceAssemblerSupport<Patient, PatientResource> {
	public PatientAssembler() {
		super(PatientsController.class, PatientResource.class);
	}
	
	
	@Autowired
	private NextOfKinAssembler nextofkinassembler;
	@Autowired
	private LocationAssembler locationassembler;
	@Autowired
	NextOfKinRepo nextofkinRepo;
	@Autowired
	LocationRepository locRepo;
	@Override
	public PatientResource toResource(Patient patient) {
				
		PatientResource patientRes = new PatientResource();
		patientRes.setIdRes(patient.getId());
		patientRes.setEmail(patient.getEmail());
		patientRes.setName(patient.getName());
		patientRes.setUserName(patient.getUsername());
		patientRes.setPassword(patient.getPassword());
		patientRes.setRegistrationDate(patient.getRegistrationDate());
		patientRes.setReg_status(patient.getReg_status());
		patientRes.setPhoneNumber(patient.getPhoneNumber());
		//patientRes.setNextOfKinRes(nextofkinassembler.toResource(patient.getNextOfKin()));System.out.println("next of kin name as "+ patient.getNextOfKin().getName());
		patientRes.add(new ExtendedLink(linkTo(methodOn(NextOfKinController.class).getKin(patient.getNextOfKin().getPhoneNumber())).toString(),"NEXTOFKIN","GET"));
		patientRes.add(new ExtendedLink(linkTo(methodOn(LocationController.class).getLocationByID(patient.getLocation().getId())).toString(), "LOCATION", "GET"));
		//patientRes.setLocRes(locationassembler.toResource(patient.getLocation()));
		return patientRes;
	}
	
	public List<PatientResource> toResources(List<Patient> all) {
		List<PatientResource> patientsRes = new ArrayList<PatientResource>();
		for (Patient patient : all) {
			patientsRes.add(toResource(patient));
		}
		return patientsRes;
	}
	
	
	public NextOfKin tonextOfKinEntity(NextOfKinResource nextofkinRes){

		
		NextOfKin nextKin = new NextOfKin();
		nextKin.setLocation(toLocationEntity(nextofkinRes.getLocationRes()));
		nextKin.setName(nextofkinRes.getName());
		nextKin.setPhoneNumber(nextofkinRes.getPhoneNumber());
		nextKin.setRelation(nextofkinRes.getRelation());
		this.nextofkinRepo.save(nextKin);
		return nextKin;	
	}
	
	public Location toLocationEntity(LocationResource locRes){
		Location loc = new Location();
		loc.setId(locRes.getIdRes());
		loc.setCountry(locRes.getCountry());
		loc.setProvince(locRes.getProvince());
		loc.setTown(locRes.getTown());
		this.locRepo.save(loc);
		return loc;
	}
	
	
}
