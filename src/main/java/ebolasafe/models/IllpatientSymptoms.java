package ebolasafe.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "illpatient_symptoms")
@Data
public class IllpatientSymptoms {
	@Id
	@GeneratedValue
	@Column(name = "illpatient_symptom_id")
	Long id;
	@Column(name = "phone")
	protected String phoneNumber;
	@Column(name = "symptom")
	protected String symptom;


}
