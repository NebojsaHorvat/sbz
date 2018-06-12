package drools.spring.example.disease;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import drools.spring.example.symptom.Symptom;

@Transactional(readOnly = true)
@Service
public class DiseaseServiceImpl implements DiseaseService{

	@Autowired 
	private DiseaseRepository diseaseRepository;
	
    private final KieContainer kieContainer;
    
    @Autowired
    public DiseaseServiceImpl(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
 
    }
    
	public Disease findOne(Long id) {
		return diseaseRepository.findOne(id);
	}

	public List<Disease> findAll() {
		return diseaseRepository.findAll();
	}

	@Transactional(readOnly = false)
	public Disease add(Disease disease) {
		return diseaseRepository.save(disease);
	}
	
	@Transactional(readOnly = false)
	public List<Disease> getMostLickelyDiseases(List<Symptom> symptoms) {
		List<Disease> diseases = new ArrayList<Disease>();
		KieSession kieSession = kieContainer.newKieSession();
	    kieSession.setGlobal("diseaseList", diseases);
//		kieSession.setGlobal("comboCount", 2);
		for(Symptom s: symptoms) {
			kieSession.insert(s);
		}
		kieSession.fireAllRules();
        kieSession.dispose();
		return diseases;
	}

	@Transactional(readOnly = false)
	public Disease getMostLikelyDisease(List<Symptom> symptoms) {
		// TODO Auto-generated method stub
		return null;
	}

}
