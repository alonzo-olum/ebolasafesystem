package ebolasafe.dto.assemblers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import ebolasafe.dto.resources.MessageResource;
import ebolasafe.models.Message;
import ebolasafe.restControllers.MessageController;

@Service
public class MessageAssembler extends ResourceAssemblerSupport<Message, MessageResource> {

	public MessageAssembler() {
		super(MessageController.class, MessageResource.class);
	}

	@Override
	public MessageResource toResource(Message notification) {
		MessageResource notificationresource = new MessageResource();
		notificationresource.setUsername(notification.getUsername());
		notificationresource.setDate_created(notification.getDate_created());
		notificationresource.setPhone(notification.getPhoneNumber());
		//notificationresource.setReg_status(notification.getContentid());
		notificationresource.setContent(notification.getContent());
		return notificationresource;
	}
	
	public List<MessageResource> toResources(List<Message> all) {
		List<MessageResource> newNotifications = new ArrayList<MessageResource>();
		for (Message notification : all) {
			newNotifications.add(toResource(notification));
		}
		return newNotifications;
	}
}
