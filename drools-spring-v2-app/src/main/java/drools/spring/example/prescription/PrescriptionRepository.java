package drools.spring.example.prescription;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drools.spring.example.disease.Disease;
import drools.spring.example.patient.Patient;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long>{
	
	List<Prescription> findByPatient(Patient patient);
	
	List<Prescription> findByDisease(Disease disease);
	

}
