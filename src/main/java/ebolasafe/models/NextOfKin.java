package ebolasafe.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity(name = "next_of_kin")
@EqualsAndHashCode(callSuper = false)
@Data
public class NextOfKin extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@GeneratedValue
	@Column(name = "next_of_kin_id")
	protected Long id;
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	Location location;
	
	protected String relation;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "nextOfKin", targetEntity = Patient.class, orphanRemoval = true)
	List<Patient> patient;

}
