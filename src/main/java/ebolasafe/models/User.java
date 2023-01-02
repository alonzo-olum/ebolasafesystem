package ebolasafe.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
	@Id
	Long Id;
	String username;
	String phone;
	String password;
	boolean enabled;
	Role role;

	public enum VerifyStatus {
		APPROVED, PENDING
	}

	public enum Role {
		ADMIN, USER
	}

}
