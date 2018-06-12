package drools.spring.example.symptom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drools.spring.example.patient.Patient;
@Repository
public interface SymptomRepository extends JpaRepository<Symptom, Long>{
	
	List<Symptom> findByPatient(Patient patient);
	
}
