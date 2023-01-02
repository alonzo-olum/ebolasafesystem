package ebolasafe.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Entity
@EqualsAndHashCode(callSuper = false)
@Data
public class Illpatient extends Person{

	@GeneratedValue
	@Column(name = "patient_id")
	protected Long id;
	protected String username;
	public enum Status{
		ebola,
		cholera,
		mers,
		zika,
		H1N1,
	};
	@Enumerated(EnumType.ORDINAL)
	protected Status status;
	protected String contact_mode;
	protected String relationship;
	@Temporal(TemporalType.DATE)
	protected Date registrationDate;
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	Location location;
	@ManyToMany
	@JoinTable(name = "illpatient_symptoms", joinColumns={@JoinColumn(name = "phone")}, inverseJoinColumns={@JoinColumn(name="symptom")} )
	protected List<Symptoms> symtoms;
	

	
	
}
