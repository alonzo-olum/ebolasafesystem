package ebolasafe.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "patient")
@EqualsAndHashCode(callSuper = false)
@Data
public class Patient extends Illpatient{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7043867261206221450L;
	
	protected String password;
	protected String reg_status;
		
	@ManyToOne(optional=false)
    @JoinColumn(name = "next_of_kin_id", nullable = false) 
	private NextOfKin nextOfKin;
	
	
	
	
}
