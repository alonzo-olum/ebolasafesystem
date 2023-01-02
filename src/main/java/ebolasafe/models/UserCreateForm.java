package ebolasafe.models;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import ebolasafe.models.User.Role;

public class UserCreateForm {
		@NotEmpty
		private String username = "";
	 	@NotEmpty
	    private String phone = "";
	    @NotEmpty
	    private String password = "";
	    @NotEmpty
	    private String passwordRepeated = "";
	    @NotNull
	    private Role role = Role.USER;

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPasswordRepeated() {
			return passwordRepeated;
		}

		public void setPasswordRepeated(String passwordRepeated) {
			this.passwordRepeated = passwordRepeated;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

	    
}
