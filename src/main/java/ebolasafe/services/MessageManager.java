package ebolasafe.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebolasafe.dto.resources.MessageResource;
import ebolasafe.exceptions.NotificationNotFoundException;
import ebolasafe.models.Content;
import ebolasafe.models.ContentIdentifier;
import ebolasafe.models.Message;
import ebolasafe.repositories.MessageRepository;
import ebolasafe.repositories.SavedUserRepository;

@Service
public class MessageManager {
	static final Logger LOG = Logger.getLogger(LoginService.class.getName());

	MessageRepository msgrepository;
	SavedUserRepository savedrepository;
	ContentIdentifier _reg_status;
	Content content = new Content();

	@Autowired
	public MessageManager(MessageRepository msgrepository,
			SavedUserRepository savedrepository) {
		super();
		this.msgrepository = msgrepository;
		this.savedrepository = savedrepository;
	}

	public List<Message> getAllNotifications() {
		// TODO Auto-generated method stub
		return this.msgrepository.findAll();
	}

	public Message createMessages(MessageResource msgRes) {
		// TODO Auto-generated method stub

		Message msg = new Message();
		String username = msgRes.getUsername();
		String reg_status = msgRes.getReg_status();
		Date date = msgRes.getDate_created();
		String phone = msgRes.getPhone();

		return this.msgrepository.save(contentGenerator(username, phone,
				reg_status, date, msg));
	}

	public List<Message> getMessages(String phone) {
		// TODO Auto-generated method stub
		return this.msgrepository.finderMethod(phone);
	}

	public Message updateNotification(Long Id, MessageResource _Notification) {
		// TODO Auto-generated method stub

		Message msg = this.msgrepository.findOne(Id);

		String username = _Notification.getUsername();
		String reg_status = _Notification.getReg_status();
		Date date = _Notification.getDate_created();
		String phone = _Notification.getPhone();

		return this.msgrepository.save(contentGenerator(username, phone,
				reg_status, date, msg));
	}

	public void deleteNotification(Long id) {
		// TODO Auto-generated method stub
		this.msgrepository.delete(id);
	}

	public ContentIdentifier setAsEnum(String stats) {

		return ContentIdentifier.valueOf(stats.toUpperCase());

	}

	public Message contentGenerator(String username, String phonenumber,
			String reg_status, Date date, Message msg) {
		int status = 0;
		String _content = null;
		_reg_status = setAsEnum(reg_status);
		// Content identifier is the object with the message type notation
		// reg_status is the string from post params to denote the stage
		// undetaken; Login(for welcome to isolation support)
		// Or View Messages(for Rainy day n Sunny day)
		if (ContentIdentifier.WELCOME == _reg_status) {
			status = 1;

			// gets the date a user has registered into the system
			// Date dated = this.savedrepository.finderMethod(username);

			LocalDate _date = new LocalDate();

			// calls the method from the Content class(contains the message
			// content)
			// returning the message string which is a member in the Message
			// object.
			_content = content.getContent(status, username, _date);
			LOG.info("The content is...." + _content);
			msg.setUsername(username);
			msg.setDate_created(date);
			msg.setContent(_content);
			msg.setPhoneNumber(phonenumber);
			msg.setContentid(_reg_status);

		} else if (ContentIdentifier.NOK_NOTIFIER == _reg_status) {
			status = 2;
			// gets the last logged date not necessary in this case as the
			// compliance is only for a registered next of kin
			Date dated = this.savedrepository.finderMethod(phonenumber);

			LocalDate _date = converTojorda(dated);

			// calls the method from the Content class(contains the message
			// content)
			// returning the message string which is a member in the Message
			// object.
			_content = content.getContent(status, username, _date);
			LOG.info("The content is...." + _content);
			msg.setUsername(username);
			msg.setDate_created(date);
			msg.setContent(_content);
			msg.setPhoneNumber(phonenumber);
			msg.setContentid(_reg_status);

		} else if (ContentIdentifier.SUNNYDAY == _reg_status) {
			status = 3;
			// gets the last logged date not necessary in this case as the
			// compliance is only for a registered next of kin
			Date dated = this.savedrepository.finderMethod(phonenumber);

			LocalDate _date = converTojorda(dated);

			// calls the method from the Content class(contains the message
			// content)
			// returning the message string which is a member in the Message
			// object.
			_content = content.getContent(status, username, _date);
			LOG.info("The content is...." + _content);
			msg.setUsername(username);
			msg.setDate_created(date);
			msg.setContent(_content);
			msg.setPhoneNumber(phonenumber);
			msg.setContentid(_reg_status);

		} else if (ContentIdentifier.RAINYDAY == _reg_status) {
			status = 4;
			// gets the last logged date not necessary in this case as the
			// compliance is only for a registered next of kin
			Date dated = this.savedrepository.finderMethod(phonenumber);

			LocalDate _date = converTojorda(dated);

			// calls the method from the Content class(contains the message
			// content)
			// returning the message string which is a member in the Message
			// object.
			_content = content.getContent(status, username, _date);
			LOG.info("The content is...." + _content);
			msg.setUsername(username);
			msg.setDate_created(date);
			msg.setContent(_content);
			msg.setPhoneNumber(phonenumber);
			msg.setContentid(_reg_status);

		} else if (ContentIdentifier.PERIOD_NOTIFIER == _reg_status) {

			status = 5;

			// gets the last logged date for a user, to determine the days spent
			// in isolation.
			Date dated = this.savedrepository.finderMethod(phonenumber);

			if (dated == null) {
				dated = new Date();
			}

			System.out.println("the last logged date...." + dated);
			LocalDate _date = converTojorda(dated);
			// calls the method from the Content class(contains the message
			// content)
			// returning the message string which is a member in the Message
			// object.
			_content = content.getContent(status, username, _date);

			msg.setUsername(username);
			msg.setDate_created(date);
			msg.setContent(_content);
			msg.setPhoneNumber(phonenumber);
			msg.setContentid(_reg_status);
		}

		if (msg == null) {

			throw new NotificationNotFoundException("No message to save");
		}

		return msg;

	}

	private LocalDate converTojorda(Date date) {
		if (date == null)
			return null;
		return new LocalDate(date);
	}
	
	public String getEarlistDate(String phone){
		java.sql.Date date = savedrepository.finderMethod(phone);
		//LocalDate _date = converTojorda(date);
		SimpleDateFormat ft = new SimpleDateFormat("YYYY-MM-dd");
		String dated =	ft.format(date);
		return dated;
	}

}
