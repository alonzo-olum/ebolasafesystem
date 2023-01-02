package ebolasafe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ebolasafe.models.Contacts;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long>{
	@Query(value = "SELECT * FROM Contacts WHERE location_id = ?1", nativeQuery = true)
	List<Contacts> finderMethod(Long id);
	
	
}
