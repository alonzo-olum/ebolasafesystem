package ebolasafe.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity(name = "saveduser")
@Data
public class SavedUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userid")
	Long userid;
	String username;
	String phone;
	String password;
	@Temporal(TemporalType.DATE)
	Date date_logged;
	String reg_status;

}
