package ebolasafe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ebolasafe.models.Authority;



@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	
	@Query("SELECT p FROM Authority p WHERE p.username.username = :username")
	public List<Authority> findByUsername(@Param("username") String username);
}
