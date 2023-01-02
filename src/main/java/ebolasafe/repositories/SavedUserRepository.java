package ebolasafe.repositories;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ebolasafe.models.SavedUser;

@Repository
public interface SavedUserRepository extends JpaRepository<SavedUser, Long> {
	@Query("select min(s.date_logged) from saveduser s where s.phone=?1")
	Date finderMethod(String phone);
	
	SavedUser findByUserid(Long id);
	
	SavedUser findByUsername(String username);

}
