package ebolasafe.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ebolasafe.models.Temperature;

@Repository
public interface TemperatureRepository extends JpaRepository <Temperature, Long>{
	
	List<Temperature> findByPhone(String phone);
	@Query("select T from Temperature T where T.phone = ?1 and T.date_taken = ?2")
	List<Temperature> datefinder(String phone, Date date);
	
}
