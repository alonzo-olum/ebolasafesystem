package ebolasafe.dto.resources;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ebolasafe.util.ResourceSupport;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ReportResource extends ResourceSupport{
	Long idRes;
	double AverageTemp;
	
	@DateTimeFormat(iso=ISO.DATE)
	Date createdDate;
	
	List<SymptomsResource> symptresources;
	List<TemperatureResource> tempResources;
}
