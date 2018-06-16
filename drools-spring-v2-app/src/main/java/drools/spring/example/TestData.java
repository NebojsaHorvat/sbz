package drools.spring.example;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import drools.spring.example.disease.Disease;
import drools.spring.example.disease.DiseaseService;
import drools.spring.example.disease.DiseaseType;
import drools.spring.example.medicine.Medicine;
import drools.spring.example.medicine.MedicineService;
import drools.spring.example.medicine.MedicineType;
import drools.spring.example.patient.Patient;
import drools.spring.example.patient.PatientService;
import drools.spring.example.symptom.Symptom;
import drools.spring.example.symptom.SymptomService;
import drools.spring.example.symptom.SymptomType;
import drools.spring.example.users.User;
import drools.spring.example.users.UserService;
import drools.spring.example.users.UserType;

@Component
public class TestData {

	@Autowired
	private PatientService patientService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DiseaseService diseaseService;
	
	@Autowired
	private SymptomService symptomService;
	
	@Autowired
	private MedicineService medicineService;
	
	
	@PostConstruct
	private void init() {
		
		User user1 = new User("ddd@ddd", "qweqwe", "Chewbacca", "Chewbacca", "Kashyyyk", null);
		user1.setUserType(UserType.DOCTOR);
		userService.register(user1);
		
		User user2 = new User("eee@eee", "qweqwe", "Richard", "Stallman", "New York", null);
		user2.setUserType(UserType.DOCTOR);
		userService.register(user2);
		
		User user3 = new User("admin@admin", "qweqwe", "Han", "Solo", "Corellia", null);
		user3.setUserType(UserType.SYSADMIN);
		userService.register(user3);
		
		Patient p1 = new Patient("Nebojsa","Horvat","penicilin,taurin");
		patientService.add(p1);
		Patient p2 = new Patient("Borislav","Puzic","vitamin");
		patientService.add(p2);
		
		Disease disease1 = new Disease(p1, DiseaseType.GROZNICA, 1528892785341L, 1.0, 7L, 1,null);
		diseaseService.add(disease1);
		
		Disease disease2 = new Disease(p1, DiseaseType.HIPERTENZIJA, 1262304000000L, 1.0, 7L, 1,null);
		diseaseService.add(disease2);
		
		for(int i = 0; i < 12 ; i++) {
			Symptom s = new Symptom(p1, SymptomType.VISOK_PRITISAK, System.currentTimeMillis());
			symptomService.add(s);
		}
		
		Medicine m1 = new Medicine(MedicineType.ANTIBIOTIK, "penicili,taurin", "Palitrex");
		medicineService.add(m1);
		Medicine m2 = new Medicine(MedicineType.ANALGETIK, "aspirin,vitamin", "Aspirin");
		medicineService.add(m2);
		p1.addAlergicMedicine(m2);
		patientService.add(p1);
		
		for(int i = 0; i < 6; i++) {
			Disease disease = new Disease(p2, DiseaseType.UPALA_KRAJNIKA
					, 1529108027000L, 1, 3L, 1, user1);
			diseaseService.add(disease);
		}

	}
}
