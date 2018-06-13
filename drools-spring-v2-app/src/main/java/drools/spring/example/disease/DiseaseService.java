package drools.spring.example.disease;

import java.util.List;

import drools.spring.example.patient.Patient;
import drools.spring.example.symptom.Symptom;

public interface DiseaseService {

	Disease findOne(Long id);
	
	List<Disease> findAll();
	
	Disease add(Disease disease);
	
	List<Disease> getMostLickelyDiseases(List<Symptom> symptoms,Patient patient);
	
	Disease getMostLikelyDisease(List<Symptom> symptoms);

	List<Disease> createSympomsReturnDiseases(SymptomTypeListDTO symptomTypesList);
}
