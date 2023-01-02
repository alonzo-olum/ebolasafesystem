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

import ebolasafe.dto.assemblers.ContactsAssembler;
import ebolasafe.dto.resources.ContactsResource;
import ebolasafe.models.Contacts;
import ebolasafe.repositories.ContactsRepository;
import ebolasafe.services.ContactsManager;

@RestController
@RequestMapping("rest/contacts")
public class ContactsController {
	
	ContactsAssembler contactsAssembler = new ContactsAssembler();
	@Autowired
	ContactsRepository contactsRepo;
	@Autowired
	ContactsManager contactsManager;
	

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<ContactsResource> getContacs(@PathVariable Long id){
		
		List<Contacts> contacts = contactsManager.getContactsInLocation(id);
		return contactsAssembler.toResources(contacts);
		
	}
	@RequestMapping(method = RequestMethod.POST, value = "")
	@ResponseStatus(HttpStatus.CREATED)
	public ContactsResource createContact(@RequestBody ContactsResource contactsRes){
		
		Contacts contacts = contactsManager.createContacts(contactsRes);
		
		return contactsAssembler.toResource(contacts);
		
	}
}
