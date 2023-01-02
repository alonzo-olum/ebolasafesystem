package ebolasafe.dto.assemblers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import ebolasafe.dto.resources.ContactsResource;
import ebolasafe.dto.resources.LocationResource;
import ebolasafe.models.Contacts;
import ebolasafe.models.Location;
import ebolasafe.repositories.LocationRepository;
import ebolasafe.restControllers.ContactsController;
import ebolasafe.restControllers.LocationController;
import ebolasafe.util.ExtendedLink;

@Service
public class ContactsAssembler extends
		ResourceAssemblerSupport<Contacts, ContactsResource> {

	public ContactsAssembler() {
		super(ContactsController.class, ContactsResource.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	LocationAssembler locAssembler;
	@Autowired
	LocationRepository locationRepo;

	@Override
	public ContactsResource toResource(Contacts entity) {
		// TODO Auto-generated method stub
		ContactsResource contRes = new ContactsResource();
		contRes.setIdRes(entity.getId());
		contRes.setDate_created(entity.getDate_created());
		contRes.setContact(entity.getContact());
		contRes.add(new ExtendedLink(linkTo(
				methodOn(LocationController.class).getLocationByID(
						entity.getLocation().getId())).toString(), "LOCATION",
				"GET"));
		// contRes.setLocRes(locAssembler.toResource(entity.getLocation()));
		return contRes;
	}

	public List<ContactsResource> toResources(List<Contacts> entities) {

		List<ContactsResource> cRes = new ArrayList<ContactsResource>();
		for (Contacts c : entities) {
			cRes.add(toResource(c));
		}

		return cRes;

	}

	public Location toLocationEntity(LocationResource locRes) {
		Location loc = new Location();
		//loc.setId(locRes.getIdRes());
		loc.setCountry(locRes.getCountry());
		loc.setProvince(locRes.getProvince());
		loc.setTown(locRes.getTown());
		this.locationRepo.save(loc);
		return loc;

	}

}
