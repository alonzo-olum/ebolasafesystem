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

import ebolasafe.dto.assemblers.MessageAssembler;
import ebolasafe.dto.resources.MessageResource;
import ebolasafe.exceptions.NotificationNotFoundException;
import ebolasafe.models.Message;
import ebolasafe.repositories.MessageRepository;
import ebolasafe.services.MessageManager;

@RestController
@RequestMapping("/rest/messages")
public class MessageController {
	MessageAssembler assembler = new MessageAssembler();
	
	@Autowired
	MessageManager manager;
	
	@Autowired
	MessageRepository repo;
	
	@RequestMapping(method = RequestMethod.GET, value = "")
	public List<MessageResource> getAllNotifications()
			throws Exception {
		List<Message> Notifications = manager.getAllNotifications();
		return assembler.toResources(Notifications);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "")
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResource createNotification(
			@RequestBody MessageResource _Notification) throws Exception {
		
		Message Notification = manager.createMessages(_Notification);
		if(Notification == null){
			throw new NotificationNotFoundException("the message has not been received for user!");
		}
		return assembler.toResource(Notification);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{phone}")
	public List<MessageResource> getNotification(
			@PathVariable("phone") String phone) {
		List<Message> Notifications = manager.getMessages(phone);
		if (Notifications == null)
			throw new NotificationNotFoundException("Message not found!");
		return assembler.toResources(Notifications);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public MessageResource updateNotification(@PathVariable("id") Long id,
			@RequestBody MessageResource _Notification) {
		Message Notification = manager.updateNotification(id,_Notification);
		return assembler.toResource(Notification);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteNotification(@PathVariable("id") Long id) {
		 manager.deleteNotification(id);
		
	}
	@RequestMapping(method = RequestMethod.GET, value="/get/{phone}")
	public ResponseEntity<String> getEarliestDate(@PathVariable("phone") String phone){
		String date = manager.getEarlistDate(phone);
		return new ResponseEntity<String>(date, HttpStatus.OK);
	}
}
