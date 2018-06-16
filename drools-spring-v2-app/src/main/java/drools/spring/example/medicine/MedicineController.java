package drools.spring.example.medicine;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import drools.spring.example.prescription.Prescription;
import drools.spring.example.prescription.PrescriptionService;
import drools.spring.example.users.User;
import drools.spring.example.users.UserType;

@RestController
@RequestMapping("/api/medicine")
public class MedicineController {

	@Autowired
	private MedicineService medicineService;
	
	@Autowired
	private PrescriptionService prescriptionService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping
	public ResponseEntity<List<Medicine>> getPatients(){
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<List<Medicine>>(HttpStatus.FORBIDDEN);
		if(user.getUserType() != UserType.DOCTOR)
			return new ResponseEntity<List<Medicine>>(HttpStatus.FORBIDDEN);
		
		List<Medicine> medicines = medicineService.findAll();
		if(medicines == null || medicines.isEmpty())
			return new ResponseEntity<List<Medicine>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Medicine>>(medicines,HttpStatus.OK);
	}
	
	@GetMapping("/{id:\\d+}")
	public ResponseEntity<Medicine> getPatient(@PathVariable Long id){
		
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<Medicine>(HttpStatus.FORBIDDEN);
		if(user.getUserType() != UserType.DOCTOR)
			return new ResponseEntity<Medicine>(HttpStatus.FORBIDDEN);
		
		Medicine medicine = medicineService.findOne(id);
		if(medicine == null)
			return new ResponseEntity<Medicine>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Medicine>(medicine,HttpStatus.OK);
	}
	@DeleteMapping("/{id:\\d+}")
	public ResponseEntity<Medicine> delete(@PathVariable Long id){
		
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<Medicine>(HttpStatus.FORBIDDEN);
		if(user.getUserType() != UserType.DOCTOR)
			return new ResponseEntity<Medicine>(HttpStatus.FORBIDDEN);
		
		Medicine medicine = medicineService.delete(id);
		if(medicine == null)
			return new ResponseEntity<Medicine>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Medicine>(medicine,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Medicine> add (@RequestBody @Valid Medicine input)
	{
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<Medicine>(HttpStatus.FORBIDDEN);
		if(user.getUserType() != UserType.DOCTOR)
			return new ResponseEntity<Medicine>(HttpStatus.FORBIDDEN);
		
		
		Medicine medicine = medicineService.add(input);
		if(medicine == null)
			return new ResponseEntity<Medicine>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Medicine>(medicine,HttpStatus.OK);
	}
	
	@GetMapping("alergies/{patientId:\\d+}/{medicineId:\\d+}")
	public ResponseEntity<String> getPatient(@PathVariable Long patientId,@PathVariable Long medicineId){
		
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		if(user.getUserType() != UserType.DOCTOR)
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		
		String message = medicineService.checkAlergies(patientId, medicineId);
		if(message == null)
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		message = "{ \"message\" : \" "+message+"\"}";
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	@GetMapping("/prescribe/{patientId:\\d+}/{medicineId:\\d+}/{diseaseId:\\d+}")
	public ResponseEntity<Prescription> addWithIds (
			 @PathVariable Long patientId
			,@PathVariable Long medicineId
			,@PathVariable Long diseaseId){
		
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<Prescription>(HttpStatus.FORBIDDEN);
		if(user.getUserType() != UserType.DOCTOR)
			return new ResponseEntity<Prescription>(HttpStatus.FORBIDDEN);
		
		Prescription prescrition = prescriptionService.addPrescription(patientId, medicineId,diseaseId,user);
		if(prescrition == null)
			return new ResponseEntity<Prescription>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Prescription>(prescrition,HttpStatus.OK);
	}
}
