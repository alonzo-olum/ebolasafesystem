package ebolasafe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ebolasafe.models.User;

@Repository
public interface UsersRepository extends JpaRepository <User, Long>{

	User findByPhone(String Phone);
	User findByUsername(String Username);
}
