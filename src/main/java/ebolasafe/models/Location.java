package ebolasafe.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	protected String country;
	protected String town;
	protected String province;
			
	@OneToMany(mappedBy = "location", cascade = CascadeType.ALL, targetEntity = Illpatient.class)
	List<Illpatient> illpatients;
	
	
}
