package ebolasafe.dto.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ebolasafe.util.ResourceSupport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class IllpatientSymptomsResource extends ResourceSupport {
	Long idRes;
	String phone;
	String symptom;

}
