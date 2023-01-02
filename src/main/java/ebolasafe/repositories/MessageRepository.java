package ebolasafe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ebolasafe.models.Message;

@Repository
public interface MessageRepository extends JpaRepository <Message,Long>{
	
	@Query(value = "SELECT * FROM Message m WHERE m.phone_number = ?1 ORDER BY m.date_created DESC ", nativeQuery=true)
	List<Message> finderMethod(String phone);
	
	List<Message> findByUsername(String username);

	List<Message> findByPhoneNumber(String phone);
}
