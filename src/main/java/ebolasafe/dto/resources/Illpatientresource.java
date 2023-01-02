package ebolasafe.dto.resources;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ebolasafe.util.ResourceSupport;
import ebolasafe.models.Illpatient.Status;

@Getter
@Setter
@XmlRootElement
@JsonInclude(Include.NON_NULL)
public class Illpatientresource extends ResourceSupport {

	Long idRes;
	String name;
	String username;
	String phone;
	String contact_mode;
	@DateTimeFormat(iso = ISO.DATE)
	Date registration_day;
	Status status;
	String relation;
	LocationResource locationRes;

}
