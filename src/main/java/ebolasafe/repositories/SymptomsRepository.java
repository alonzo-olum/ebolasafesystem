package ebolasafe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ebolasafe.models.Symptoms;

@Repository
public interface SymptomsRepository extends JpaRepository<Symptoms, Long>{
	
	Symptoms findBySymptom(String symptom_name);
	
	@Query(value = "SELECT * FROM Symptoms s JOIN symptoms_patient sp ON sp.symptom = s.symptom and sp.username = ?1", nativeQuery = true)
	List<Symptoms> finderMethod(String username);

}
