package drools.spring.example.disease;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import drools.spring.example.patient.Patient;
import drools.spring.example.symptom.Symptom;
import drools.spring.example.symptom.SymptomService;
import drools.spring.example.symptom.SymptomType;
import drools.spring.example.users.User;
import drools.spring.example.users.UserType;

@RestController
@RequestMapping("/api/diseases")
public class DiseaseController {
	
	@Autowired 
	private DiseaseService diseaseService;
	
	@Autowired
	private SymptomService symptomService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping()
	public ResponseEntity<List<DiseaseType>> getDiseases(){
		List<DiseaseType> diseases = Arrays.asList(DiseaseType.class.getEnumConstants());
		
		if(diseases == null || diseases.isEmpty())
			return new ResponseEntity<List<DiseaseType>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<DiseaseType>>(diseases,HttpStatus.OK);
		
	}
	
	@GetMapping("/symptoms/{name}")
	public ResponseEntity<List<SymptomType>> getDiseaseSymptoms(@PathVariable String name){
		
		DiseaseType diseaseType = DiseaseType.valueOf(name);
		Disease disease = new Disease();
		disease.setDiseaseType(diseaseType);
		
		
		List<SymptomType> symptoms = diseaseService.getDiseaseSymptoms(disease);
		
		if(symptoms == null || symptoms.isEmpty())
			return new ResponseEntity<List<SymptomType>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<SymptomType>>(symptoms,HttpStatus.OK);
	}
		
		
	
		
	@GetMapping("/symptoms")
	public ResponseEntity<List<SymptomType>> getAllSymptoms(){
		List<SymptomType> symptoms = Arrays.asList(SymptomType.class.getEnumConstants());
		
		if(symptoms == null || symptoms.isEmpty())
			return new ResponseEntity<List<SymptomType>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<SymptomType>>(symptoms,HttpStatus.OK);
		
	}
	
	@PostMapping("/reasoning")
	public ResponseEntity<List<Disease>> getListOfDiseases(@RequestBody SymptomTypeListDTO symptomTypesList){
		
		if(symptomTypesList.getSymptomTypes() == null || symptomTypesList.getSymptomTypes().size() == 0 
				|| symptomTypesList.getId() == 0)
			return new ResponseEntity<List<Disease>>(HttpStatus.BAD_REQUEST);
		
		
		List<Disease> diseases = diseaseService.createSympomsReturnDiseases(symptomTypesList);
		
		if(diseases == null || diseases.isEmpty())
			return new ResponseEntity<List<Disease>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Disease>>(diseases,HttpStatus.OK);
		
	}
	
	@PostMapping("/diagnose")
	public ResponseEntity<Disease> getListOfDiseases(@RequestBody Disease disease){
		
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<Disease>(HttpStatus.FORBIDDEN);
		if(user.getUserType() != UserType.DOCTOR)
			return new ResponseEntity<Disease>(HttpStatus.FORBIDDEN);
		disease.setUser(user);
		
		Disease ret = diseaseService.diagnoseDisease(disease);
		
		if(ret == null )
			return new ResponseEntity<Disease>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Disease>(ret,HttpStatus.OK);
		
	}

}
