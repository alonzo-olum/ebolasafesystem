package ebolasafe.dto.resources;

import com.fasterxml.jackson.annotation.JsonInclude;

import ebolasafe.models.User.VerifyStatus;
import ebolasafe.util.ResourceSupport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "user")
@JsonInclude
public class UserRegisterResource extends ResourceSupport{
    String username;
    String email;
    String password;
    VerifyStatus verifystatus;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public VerifyStatus getVerifystatus() {
		return verifystatus;
	}
	public void setVerifystatus(VerifyStatus verifystatus) {
		this.verifystatus = verifystatus;
	}
    
    
}