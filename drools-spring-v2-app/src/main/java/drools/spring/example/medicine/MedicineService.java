package drools.spring.example.medicine;

import java.util.List;

import drools.spring.example.patient.Patient;

public interface MedicineService {

	Medicine findOne(Long id);
	
	List<Medicine> findAll();
	
	Medicine add(Medicine medicine);
	
}
