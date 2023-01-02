package ebolasafe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebolasafe.dto.assemblers.ContactsAssembler;
import ebolasafe.dto.resources.ContactsResource;
import ebolasafe.models.Contacts;
import ebolasafe.repositories.ContactsRepository;

@Service
public class ContactsManager {
	
	@Autowired
	ContactsAssembler contactAssembler;
	@Autowired
	ContactsRepository contactRepo;

	public Contacts createContacts(ContactsResource coRes){
		Contacts c = new Contacts();
		c.setContact(coRes.getContact());
		c.setDate_created(coRes.getDate_created());
		c.setLocation(contactAssembler.toLocationEntity(coRes.getLocRes()));
		this.contactRepo.save(c);
		return c;
		
	}
	
	public List<Contacts> getAllContacts(){
		
		return this.contactRepo.findAll();
	}
	
	public List<Contacts> getContactsInLocation(Long id){
		return this.contactRepo.finderMethod(id);
	}
}
