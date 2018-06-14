package drools.spring.example.medicine;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drools.spring.example.patient.Patient;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long>{
	
}
