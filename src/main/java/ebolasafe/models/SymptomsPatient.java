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


@Entity(name = "symptoms_patient")
@Data
public class SymptomsPatient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "symptom_patient_id")
	protected Long spid;
	@Column(name = "phone")
	protected String phoneNumber;
	@Column(name = "username")
	protected String username;
	@Column(name = "symptom")
	protected String symptom;
	@Temporal(TemporalType.DATE)
	protected Date date_added;

}
