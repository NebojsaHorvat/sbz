package drools.spring.example.patient;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import drools.spring.example.medicine.Medicine;
import drools.spring.example.users.User;
import drools.spring.example.users.UserType;



@RestController
@RequestMapping("/api/patients")
public class PatientController {

	@Autowired
	private PatientService patientService;
	@Autowired
	private HttpSession session;
	
	@GetMapping
	public ResponseEntity<List<Patient>> getPatients(){
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<List<Patient>>(HttpStatus.FORBIDDEN);
		if(user.getUserType() != UserType.DOCTOR)
			return new ResponseEntity<List<Patient>>(HttpStatus.FORBIDDEN);
		
		List<Patient> patients = patientService.findAll();
		if(patients == null || patients.isEmpty())
			return new ResponseEntity<List<Patient>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Patient>>(patients,HttpStatus.OK);
	}
	
	@GetMapping("/{id:\\d+}")
	public ResponseEntity<Patient> getPatient(@PathVariable Long id){
		
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<Patient>(HttpStatus.FORBIDDEN);
		if(user.getUserType() != UserType.DOCTOR)
			return new ResponseEntity<Patient>(HttpStatus.FORBIDDEN);
		
		Patient patient = patientService.findOne(id);
		if(patient == null)
			return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Patient>(patient,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Patient> add (@RequestBody @Valid Patient input)
	{
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<Patient>(HttpStatus.FORBIDDEN);
		if(user.getUserType() != UserType.DOCTOR)
			return new ResponseEntity<Patient>(HttpStatus.FORBIDDEN);
		
		
		Patient patient = patientService.add(input);
		if(patient == null)
			return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Patient>(patient,HttpStatus.OK);
	}
	
}
