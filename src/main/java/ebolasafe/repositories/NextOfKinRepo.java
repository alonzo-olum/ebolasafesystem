package ebolasafe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ebolasafe.models.NextOfKin;

@Repository
public interface NextOfKinRepo extends JpaRepository<NextOfKin, Long>{
	
	@Query(value = "select n from next_of_kin n where n.phoneNumber = ?1")
	NextOfKin finderMethod(String phone);

}
