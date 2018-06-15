package drools.spring.example.events;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import drools.spring.example.symptom.SymptomType;

@RestController
@RequestMapping("/api/monitoring")
public class MonitoringController {

	@GetMapping("/heartBeat")
	public ResponseEntity<List<SymptomType>> getAllSymptoms(){
		List<SymptomType> symptoms = Arrays.asList(SymptomType.class.getEnumConstants());
		
		if(symptoms == null || symptoms.isEmpty())
			return new ResponseEntity<List<SymptomType>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<SymptomType>>(symptoms,HttpStatus.OK);
		
	}
}
