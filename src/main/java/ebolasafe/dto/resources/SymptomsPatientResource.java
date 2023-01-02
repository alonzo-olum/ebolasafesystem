package ebolasafe.dto.resources;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ebolasafe.util.ResourceSupport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class SymptomsPatientResource extends ResourceSupport {
	Long idRes;
	String phone;
	String symptom;
	Date date_added;
	String username;

}
