package drools.spring.example;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import drools.spring.example.patient.Patient;
import drools.spring.example.patient.PatientService;

@Component
public class TestData {

	@Autowired
	private PatientService patientService;
	
	@PostConstruct
	private void init() {
		Patient p1 = new Patient("Nebojsa","Horvat");
		patientService.add(p1);
		Patient p2 = new Patient("Borislav","Puzic");
		patientService.add(p2);
		
	}
}
