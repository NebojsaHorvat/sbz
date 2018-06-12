package drools.spring.example.disease;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drools.spring.example.patient.Patient;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long>{

	List<Disease> findByPatient(Patient patient);
	
}
