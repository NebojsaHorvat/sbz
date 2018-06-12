package drools.spring.example.patient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/patients")
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	@GetMapping
	public ResponseEntity<List<Patient>> getPatients(){
		List<Patient> patients = patientService.findAll();
		if(patients == null || patients.isEmpty())
			return new ResponseEntity<List<Patient>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Patient>>(patients,HttpStatus.OK);
	}
	
	@GetMapping("/{id:\\d+}")
	public ResponseEntity<Patient> getPatient(@PathVariable Long id){
		Patient patient = patientService.findOne(id);
		if(patient == null)
			return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Patient>(patient,HttpStatus.OK);
	}
}
