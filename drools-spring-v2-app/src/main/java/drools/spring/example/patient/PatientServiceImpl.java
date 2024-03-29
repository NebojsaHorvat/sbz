package drools.spring.example.patient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import drools.spring.example.disease.Disease;
import drools.spring.example.disease.DiseaseService;
import drools.spring.example.medicine.Message;
import drools.spring.example.prescription.Prescription;
import drools.spring.example.prescription.PrescriptionService;

@Transactional(readOnly = true)
@Service
public class PatientServiceImpl implements PatientService{

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private DiseaseService diseaseService;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private PrescriptionService prescriptionService;
	
	private final KieContainer kieContainer;
	
	@Autowired
    public PatientServiceImpl(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
 
    }
	public Patient findOne(Long id) {
		return patientRepository.findById(id).get();
	}

	public List<Patient> findAll() {
		List<Patient> patients = patientRepository.findAll();
		List<Patient> ret = new ArrayList<Patient>();
		for(Patient p: patients) {
			if(p.isDeleted())
				continue;
			ret.add(p);
		}
		return ret;
		
	}

	@Transactional(readOnly = false)
	public Patient add(Patient patient) {
		return patientRepository.save(patient);
		
	}
	
	@Transactional(readOnly = false)
	public Patient delete(Long id) {
		
		Patient patient = null;
		try {
			patient =findOne(id);
			patient.setDeleted(true);
			patientRepository.save(patient);
			
		}catch(Exception e) {
			return null;
		}
		return patient;
	}

	@Transactional(readOnly = false)
	public Patient edit(Long id, Patient patent) {
		// TODO Auto-generated method stub
		return null;
	}

	public Message getChronical() {
	    
		KieSession kieSession = kieContainer.newKieSession("reportsSession");
	    Long nowGlobal = System.currentTimeMillis();
	    kieSession.setGlobal("nowGlobal", nowGlobal);
	    Set<String> stringSet = new HashSet<String>();
	    kieSession.setGlobal("stringSet", stringSet);
	    
	    List<Disease> diseases = diseaseService.findAll();
	    for(Disease d: diseases)
	    	kieSession.insert(d);
	    kieSession.getAgenda().getAgendaGroup("reports").setFocus();
	    kieSession.fireAllRules();
        kieSession.dispose();
        Message customMessage = new Message();
        for(String s: stringSet) {
        	customMessage.message += s + ",";
        }
	    return customMessage;
	}

	public Message getAdiction() {
		KieSession kieSession = kieContainer.newKieSession("reportsSession");
	    Long nowGlobal = System.currentTimeMillis();
	    kieSession.setGlobal("nowGlobal", nowGlobal);
	    Set<String> stringSet = new HashSet<String>();
	    kieSession.setGlobal("stringSet", stringSet);
	    
	    // Ubacujem sve pacijente i prepisane lekove
	    List<Patient> patients = patientService.findAll();
	    for(Patient p : patients) 
	    	kieSession.insert(p);
	    List<Prescription> prescriptions = prescriptionService.findAll();
	    for(Prescription p : prescriptions)
	    	kieSession.insert(p);
	    
	    kieSession.getAgenda().getAgendaGroup("reports").setFocus();
	    kieSession.fireAllRules();
        kieSession.dispose();
	    Message customMessage = new Message();
        for(String s: stringSet) {
        	customMessage.message += s + ",";
        }
	    return customMessage; 
	}

	public Message getImmunity() {
		
		KieSession kieSession = kieContainer.newKieSession("reportsSession");
	    Long nowGlobal = System.currentTimeMillis();
	    kieSession.setGlobal("nowGlobal", nowGlobal);
	    Set<String> stringSet = new HashSet<String>();
	    kieSession.setGlobal("stringSet", stringSet);
	    
	    // Ubacujem sve pacijente i prepisane lekove
	    List<Patient> patients = patientService.findAll();
	    for(Patient p : patients) 
	    	kieSession.insert(p);
	    List<Prescription> prescriptions = prescriptionService.findAll();
	    for(Prescription p : prescriptions)
	    	kieSession.insert(p);
	    
	    kieSession.getAgenda().getAgendaGroup("imunitet").setFocus();
	    kieSession.fireAllRules();
        kieSession.dispose();
	    Message customMessage = new Message();
        for(String s: stringSet) {
        	customMessage.message += s + ",";
        }
	    return customMessage;
	}
	
	
}
