package drools.spring.example.disease;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import drools.spring.example.patient.Patient;
import drools.spring.example.patient.PatientService;
import drools.spring.example.symptom.Symptom;
import drools.spring.example.symptom.SymptomService;
import drools.spring.example.symptom.SymptomType;

@Transactional(readOnly = true)
@Service
public class DiseaseServiceImpl implements DiseaseService{

	@Autowired 
	private DiseaseRepository diseaseRepository;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private SymptomService symptomService;
	
    private final KieContainer kieContainer;
    
    @Autowired
    public DiseaseServiceImpl(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
 
    }
    
	public Disease findOne(Long id) {
		return diseaseRepository.findById(id).get();
	}

	public List<Disease> findAll() {
		return diseaseRepository.findAll();
	}

	@Transactional(readOnly = false)
	public Disease add(Disease disease) {
		return diseaseRepository.save(disease);
	}
	
	@Transactional(readOnly = false)
	public List<Disease> getMostLickelyDiseases(List<Symptom> symptoms,Patient patient) {
		List<Disease> diseases = new ArrayList<Disease>();
		KieSession kieSession = kieContainer.newKieSession();
	    kieSession.setGlobal("diseaseList", diseases);
	    Long now1 = System.currentTimeMillis();
	    kieSession.setGlobal("now1", now1);

		for(Symptom s: symptoms) {
			kieSession.insert(s);
		}
		// Treba i ubaciti sve boleski od kojih se pacijent bolovao u rezoner
		List<Disease> oldDiseases = diseaseRepository.findByPatient(patient);
		for(Disease d: oldDiseases) {
			kieSession.insert(d);
		}
		
		kieSession.fireAllRules();
        kieSession.dispose();
		return diseases;
	}
	
	public List<SymptomType> getDiseaseSymptoms(Disease disease) {
		List<SymptomType> symptomTypes = new ArrayList<SymptomType>();
		KieSession kieSession = kieContainer.newKieSession();
	    kieSession.setGlobal("symptomTypes", symptomTypes);
	    kieSession.insert(disease);
	    kieSession.getAgenda().getAgendaGroup("diseaseSymptoms").setFocus();
	    kieSession.fireAllRules();
	    kieSession.dispose();
		return symptomTypes;
	}

	@Transactional(readOnly = false)
	public Disease getMostLikelyDisease(List<Symptom> symptoms) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional(readOnly = false)
	public List<Disease> createSympomsReturnDiseases(SymptomTypeListDTO symptomTypesList) {
		Long id = (long) symptomTypesList.getId();
		Patient patient = patientService.findOne(id);
		if(patient == null)
			return null;
		List<Symptom> symptoms = new ArrayList();
		for(SymptomType st : symptomTypesList.getSymptomTypes()) {
			Symptom symptom = new Symptom(patient, st, System.currentTimeMillis());
			symptoms.add(symptom);
		}
		// Sada treba dodate simptome sacuvati
		symptomService.saveSymptoms(symptoms);
		
		// Preuzmi iz baze simptome od pre koji su sad relevantni
		List<Symptom> additionlSymptoms = addAditionalSymptoms(patient);
		symptoms.addAll(additionlSymptoms);
		List<Disease> diseases = getMostLickelyDiseases(symptoms,patient);
		
		for(Disease d : diseases) {
			d.setTimeStamp(System.currentTimeMillis());
			d.setPatient(patient);
		}
		// Treba i sacuvati sve bolesti
		//diseaseRepository.saveAll(diseases);
		// NE, TREBA SACUVATI SAMO ONE KOJE SE GIJAGNOSTIKUJU

		return diseases;
	}

	private List<Symptom> addAditionalSymptoms(Patient patient) {
		List<Symptom> allSymptoms = symptomService.findByPatient(patient);
		List<Symptom> additionalSymptoms = new ArrayList<Symptom>();
		for(Symptom s: allSymptoms) {
			if(s.getSymptomType() == SymptomType.VISOK_PRITISAK)
				additionalSymptoms.add(s);
		}
		return additionalSymptoms;
	}

	

}
