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
import drools.spring.example.prescription.Prescription;
import drools.spring.example.prescription.PrescriptionService;
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
	
	@Autowired
	private PrescriptionService prescriptionService;
	
	@PostConstruct
	private void init() {
		
		User user1 = new User("ddd@ddd", "qweqwe", "Chewbacca", "Chewbacca", "Kashyyyk", null);
		user1.setUserType(UserType.DOCTOR);
		userService.register(user1);
		
		User user2 = new User("eee@eee", "qweqwe", "Richard", "Stallman", "New York", null);
		user2.setUserType(UserType.DOCTOR);
		userService.register(user2);
		
		User user3 = new User("ccc@ccc", "qweqwe", "Richard", "Stallman", "New York", null);
		user3.setUserType(UserType.DOCTOR);
		userService.register(user3);
		
		User user4 = new User("admin@admin", "qweqwe", "Han", "Solo", "Corellia", null);
		user4.setUserType(UserType.SYSADMIN);
		userService.register(user4);
		
		Patient p1 = new Patient("Nebojsa","Horvat","penicilin,taurin");
		patientService.add(p1);
		Patient p2 = new Patient("Borislav","Puzic","vitamin");
		patientService.add(p2);
		Patient p3 = new Patient("Marko","Trivunovic","zeleno");
		patientService.add(p3);
		Patient p4 = new Patient("Luka","Solaja","zeleno,zeleno");
		patientService.add(p4);

		patientService.add(p3);
		
		Disease disease1 = new Disease(p1, DiseaseType.GROZNICA, 1528892785341L, 1.0, 7L, 1,null);
		disease1.setTimeStamp(1529163882000L);
		diseaseService.add(disease1);
		
		Disease disease2 = new Disease(p1, DiseaseType.HIPERTENZIJA, 1262304000000L, 1.0, 7L, 1,null);
		disease2.setTimeStamp(1529163882000L);
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
		
		for(int i=0 ;i < 6 ;i ++) {
			Prescription p = new Prescription(p3, disease1, user1, m1);
			if(i%3 == 0)
				p.setUser(user1);
			else if(i%3 == 1)
				p.setUser(user2);
			else if(i%3 == 2)
				p.setUser(user3);
			
			prescriptionService.add(p);
		}
		
		for(int i=0 ;i < 15 ;i ++) {
			Prescription p = new Prescription(p4, disease1, user1, m1);
			if(i >8) {
				p.setDisease(disease2);
			}
			
			prescriptionService.add(p);
		}

	}
}
