package ebolasafe.dto.resources;

import ebolasafe.util.ResourceSupport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "authority")
public class AuthorityResource extends ResourceSupport{
	Long resId;
    String username;
    String authority;
	public Long getResId() {
		return resId;
	}
	public void setResId(Long resId) {
		this.resId = resId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
    
}