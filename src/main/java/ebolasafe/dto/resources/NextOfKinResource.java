package ebolasafe.dto.resources;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ebolasafe.util.ResourceSupport;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class NextOfKinResource extends ResourceSupport
{
	
	Long idRes;
	String name;
	String phoneNumber;
	LocationResource locationRes;
	String relation;
		
}
