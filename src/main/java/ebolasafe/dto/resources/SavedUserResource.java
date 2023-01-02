package ebolasafe.dto.resources;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ebolasafe.util.ResourceSupport;


@Setter
@Getter
@XmlRootElement
@JsonInclude(Include.NON_NULL)
public class SavedUserResource extends ResourceSupport {

	Long idRes;
	// String username;
	String phone;
	String password;
	@DateTimeFormat(iso = ISO.DATE)
	Date date_logged;
	String status;

}
