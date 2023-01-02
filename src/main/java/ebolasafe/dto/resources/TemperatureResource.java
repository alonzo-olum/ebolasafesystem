package ebolasafe.dto.resources;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ebolasafe.util.ResourceSupport;
import ebolasafe.models.Temperature.time;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class TemperatureResource extends ResourceSupport{
	Long tempId;
	String username;
	float temp;
	String phone;
	@DateTimeFormat(iso = ISO.DATE)
	Date date_taken;
	time _timeRes;
	
	//Getters and setters
		
	public String getUsername() {
		return username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Long getTempId() {
		return tempId;
	}
	public void setTempId(Long tempId) {
		this.tempId = tempId;
	}
	public time get_timeRes() {
		return _timeRes;
	}
	public void set_timeRes(time _timeRes) {
		this._timeRes = _timeRes;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public float getTemp() {
		return temp;
	}
	public void setTemp(float temp) {
		this.temp = temp;
	}
	public Date getDate_taken() {
		return date_taken;
	}
	public void setDate_taken(Date date_taken) {
		this.date_taken = date_taken;
	}
	
	
}
