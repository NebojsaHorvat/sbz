package drools.spring.example.patient;

import java.util.List;

import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import drools.spring.example.disease.Disease;
import drools.spring.example.disease.DiseaseService;
import drools.spring.example.medicine.Message;

@Transactional(readOnly = true)
@Service
public class PatientServiceImpl implements PatientService{

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private DiseaseService diseaseService;
	
	private final KieContainer kieContainer;
	
	@Autowired
    public PatientServiceImpl(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
 
    }
	public Patient findOne(Long id) {
		return patientRepository.findById(id).get();
	}

	public List<Patient> findAll() {
		return patientRepository.findAll();
	}

	@Transactional(readOnly = false)
	public Patient add(Patient patient) {
		return patientRepository.save(patient);
	}

	@Transactional(readOnly = false)
	public Patient edit(Long id, Patient patent) {
		// TODO Auto-generated method stub
		return null;
	}

	public Message getChronical() {
	    
		KieSession kieSession = kieContainer.newKieSession("reportsSession");
		Message customMessage = new Message();
	    kieSession.setGlobal("customMessage", customMessage);
	    Long nowGlobal = System.currentTimeMillis();
	    kieSession.setGlobal("nowGlobal", nowGlobal);
	    
	    List<Disease> diseases = diseaseService.findAll();
	    for(Disease d: diseases)
	    	kieSession.insert(d);
	    kieSession.getAgenda().getAgendaGroup("reports").setFocus();
	    kieSession.fireAllRules();
        kieSession.dispose();
	    return customMessage;
	}

	public Message getAdiction() {
		// TODO Auto-generated method stub
		return null;
	}

	public Message getImmunity() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
