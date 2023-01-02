package ebolasafe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ebolasafe.models.Location;

public interface LocationRepository extends JpaRepository<Location, Long>{

	Location findById(Long id);
	List<Location> findByCountry(String country);
	List<Location> findByTown(String town);
	
}
