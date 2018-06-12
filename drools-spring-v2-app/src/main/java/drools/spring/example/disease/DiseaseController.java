package drools.spring.example.disease;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import drools.spring.example.symptom.SymptomService;
import drools.spring.example.symptom.SymptomType;

@RestController
@RequestMapping("/api/diseases")
public class DiseaseController {
	
	@Autowired 
	private DiseaseService diseaseService;
	
	@Autowired
	private SymptomService symptomService;
	
	@GetMapping("/symptoms")
	public ResponseEntity<List<SymptomType>> getProjections(){
		List<SymptomType> symptoms = Arrays.asList(SymptomType.class.getEnumConstants());
		
		if(symptoms == null || symptoms.isEmpty())
			return new ResponseEntity<List<SymptomType>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<SymptomType>>(symptoms,HttpStatus.OK);
		
	}

}
