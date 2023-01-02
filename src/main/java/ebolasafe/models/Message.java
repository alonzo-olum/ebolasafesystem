package ebolasafe.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;


@Entity
@Data
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long message_id;
	protected String username;
	protected String phoneNumber;
	@Temporal(TemporalType.DATE)
	protected Date date_created;
	protected String content;
	@Enumerated(EnumType.STRING)
	protected ContentIdentifier contentid;
	
}


