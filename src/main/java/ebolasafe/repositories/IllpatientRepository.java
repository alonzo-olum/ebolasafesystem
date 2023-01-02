package ebolasafe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ebolasafe.models.Illpatient;

@Repository
public interface IllpatientRepository extends JpaRepository<Illpatient, Long> {
	
	@Query(value = "select Ill from Illpatient Ill where Ill.phoneNumber = ?1")
	Illpatient finderMethod(String phone);

}
