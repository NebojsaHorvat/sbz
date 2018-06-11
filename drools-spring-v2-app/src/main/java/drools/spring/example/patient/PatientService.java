package drools.spring.example.patient;

import java.util.List;

public interface PatientService {

	Patient findOne(Long id);
	
	List<Patient> findAll();
	
	Patient add( Patient patient);
	
	Patient edit(Long id, Patient patent);
	
	
	
}
