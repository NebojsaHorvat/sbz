package drools.spring.example.medicine;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import drools.spring.example.patient.Patient;
import drools.spring.example.patient.PatientService;

@Transactional(readOnly = true)
@Service
public class MedicineServiceImpl implements MedicineService{

	@Autowired
	private MedicineRepository medicineRepository;

	@Autowired
	private PatientService patientService;
	
	private final KieContainer kieContainer;
	
	@Autowired
    public MedicineServiceImpl(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
 
    }
	
	public Medicine findOne(Long id) {
		return  medicineRepository.findById(id).get();
		
	}

	public List<Medicine> findAll() {
		List<Medicine> medicines = medicineRepository.findAll();
		List<Medicine> ret = new ArrayList<Medicine>();
		for(Medicine m :medicines) {
			if(m.isDeleted())
				continue;
			ret.add(m);
		}
		return ret;
		
	}

	@Transactional(readOnly = false)
	public Medicine add(Medicine medicine) {
		return medicineRepository.save(medicine);
	}

	public String checkAlergies(Long patientId, Long medicineId) {
		Patient patient= patientService.findOne(patientId);
		if(patient == null)
			return null;
		Medicine medicine = findOne(medicineId);
		if(medicine == null)
			return null;
		
		Message retMessage = new Message();
		KieSession kieSession = kieContainer.newKieSession("diseasesSession");
	    kieSession.setGlobal("retMessage", retMessage);
	    kieSession.insert(patient);
	    kieSession.insert(medicine);
	    kieSession.getAgenda().getAgendaGroup("checkAlergies").setFocus();
	    kieSession.fireAllRules();
	    kieSession.dispose();
//	    retMessage = dasf(medicine,patient,retMessage);
	    if(retMessage.message.equals(""))
	    	retMessage.message = "Patient is not alergic";
		return retMessage.message;
	}
	
	public String dasf(Medicine $m,Patient $p,String message) {
		String [] patientSubstances = $p.getAlergicSubstances().split(",");
		String [] medicineSupstances = $m.getSubstances().split(",");
		for(String patientS :patientSubstances) {
			for(String medicineS: medicineSupstances) {
				if(patientS.contains(medicineS)) {
					if(message == null)
						message = "Patient is alergic to "+ medicineS +" in " +$m.getName();
					else {
						message += "\n";
						message += "Patient is alergic to "+ medicineS +" in " +$m.getName();
					}
					break;
				}
			}
		}
		return message;
	}

	@Transactional(readOnly = false)
	public Medicine delete(Long id) {
		Medicine medicine = findOne(id);
		if(medicine == null)
			return null;
		try {
		medicine.setDeleted(true);
		medicineRepository.save(medicine);
		}catch(Exception e) {
			return null;
		}
		return medicine;
	}

	
}
