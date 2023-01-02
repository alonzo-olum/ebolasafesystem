package ebolasafe.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class Symptoms implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1360729931502441855L;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "symptom_id", nullable = false)
Long id;
String symptom;
@Temporal(TemporalType.DATE)
Date date_added;

@ManyToMany
@JoinTable(name = "symptoms_patient", joinColumns = {@JoinColumn(name = "symptom")}, 
inverseJoinColumns = {@JoinColumn(name = "phone")})
List<Patient> patients;

}
