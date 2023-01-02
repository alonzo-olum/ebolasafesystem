package ebolasafe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ebolasafe.models.Patient;

@Repository
public interface PatientsRepository extends JpaRepository<Patient,Long>{
	Patient findByUsername(String username);
//extend CRUD repo......create,update,delete
	@Query(value = "Select p from patient p where p.phoneNumber = ?1")
	Patient finderMethod(String phone);
}
