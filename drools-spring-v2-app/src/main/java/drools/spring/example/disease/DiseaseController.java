package drools.spring.example.disease;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import drools.spring.example.symptom.Symptom;
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

}
