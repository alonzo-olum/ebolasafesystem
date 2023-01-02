package ebolasafe.dto.resources;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ebolasafe.util.ResourceSupport;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class LocationResource extends ResourceSupport{
	Long idRes;
	String country;
	String town;
	String province;
	
	List<PatientResource> patientsRes;

		
}
