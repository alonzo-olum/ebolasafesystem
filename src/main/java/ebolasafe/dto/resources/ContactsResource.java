package ebolasafe.dto.resources;



import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude;

import ebolasafe.util.ResourceSupport;

@Getter
@Setter
@XmlRootElement
@JsonInclude
public class ContactsResource extends ResourceSupport{

	Long IdRes;
	String contact;
	@Temporal(TemporalType.DATE)
	Date date_created;
	LocationResource locRes;
}
