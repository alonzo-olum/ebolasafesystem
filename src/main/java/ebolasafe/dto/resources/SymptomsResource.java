package ebolasafe.dto.resources;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ebolasafe.util.ResourceSupport;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class SymptomsResource extends ResourceSupport {
	Long idRes;
	String symptom;
	@Temporal(TemporalType.DATE)
	Date date_added;

}
