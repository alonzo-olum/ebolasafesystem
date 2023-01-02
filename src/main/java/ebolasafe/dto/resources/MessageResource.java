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
public class MessageResource extends ResourceSupport {
	Long idRes;
	String phone;
	String username;
	String reg_status;
	String content;
	@Temporal(TemporalType.DATE)
	Date date_created;

}
