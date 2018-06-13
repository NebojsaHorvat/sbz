package drools.spring.example.symptom;

import java.util.List;

public interface SymptomService {

	Symptom findOne(Long id);
	
	List<Symptom> findAll ();
	
	Symptom add(Symptom symptom);

	void saveSymptoms(List<Symptom> symptoms);
}
