package ebolasafe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebolasafe.models.IllpatientSymptoms;

@Repository
public interface IllpatientSymptomRepository extends JpaRepository<IllpatientSymptoms, Long> {

}
