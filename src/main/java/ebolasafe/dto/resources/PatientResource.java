package ebolasafe.dto.resources;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ebolasafe.util.ResourceSupport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class PatientResource extends ResourceSupport {
	Long idRes;
	String name;
	String userName;
	String phoneNumber;
	String password;
	String email;
	
	@DateTimeFormat(iso = ISO.DATE)
	Date registrationDate;

	protected String reg_status;

	protected NextOfKinResource nextOfKinRes;

	protected LocationResource locRes;
}
