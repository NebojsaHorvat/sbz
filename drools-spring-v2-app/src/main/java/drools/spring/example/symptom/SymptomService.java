package drools.spring.example.symptom;

import java.util.List;

import drools.spring.example.patient.Patient;

public interface SymptomService {

	Symptom findOne(Long id);
	
	List<Symptom> findAll ();
	
	List<Symptom> findByPatient (Patient patient);
	
	Symptom add(Symptom symptom);

	void saveSymptoms(List<Symptom> symptoms);
}
