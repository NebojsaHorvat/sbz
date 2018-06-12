package drools.spring.example.symptom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import drools.spring.example.patient.Patient;

@Transactional(readOnly = true)
@Service
public class SymptomServiceImpl implements SymptomService{

	@Autowired 
	private SymptomRepository symptomRepository;
	
	public Symptom findOne(Long id) {
		return symptomRepository.findById(id).get();
	}

	public List<Symptom> findAll() {
		return symptomRepository.findAll();
	}

	public Symptom add(Symptom symptom) {
		return symptomRepository.save(symptom);
	}
	
	public List<Symptom> findByPatient(Patient patient){
		return symptomRepository.findByPatient(patient);
	}

}
