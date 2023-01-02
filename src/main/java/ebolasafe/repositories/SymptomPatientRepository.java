package ebolasafe.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ebolasafe.models.SymptomsPatient;

public interface SymptomPatientRepository extends JpaRepository<SymptomsPatient, Long>{

	@Query(value = "SELECT * FROM symptoms_patient WHERE phone = ?1", nativeQuery = true)
	List<SymptomsPatient> finderMethod(String phone);
	
	SymptomsPatient findByPhoneNumber(String phone);
	
	@Query(value="select SP from symptoms_patient SP where SP.phoneNumber = ?1 and SP.date_added = ?2")
	List<SymptomsPatient> dateFinder(String phone, Date date);
	
	
}
