package drools.spring.example;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.xml.SimpleTransformErrorListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import drools.spring.example.disease.Disease;
import drools.spring.example.disease.DiseaseService;
import drools.spring.example.facts.Item;
import drools.spring.example.symptom.Symptom;
import drools.spring.example.symptom.SymptomType;

@RestController
public class SampleAppController {
	private static Logger log = LoggerFactory.getLogger(SampleAppController.class);

	private final SampleAppService sampleService;

	@Autowired
	private DiseaseService diseaseService;
	
	@Autowired
	    public SampleAppController(SampleAppService sampleService) {
	        this.sampleService = sampleService;
	    }

	@RequestMapping(value = "/item", method = RequestMethod.GET, produces = "application/json")
	public Item getQuestions(@RequestParam(required = true) String id, @RequestParam(required = true) String name, @RequestParam(required = true) double cost, @RequestParam(required = true) double salePrice) {

		Item newItem = new Item(Long.parseLong(id), name, cost, salePrice);

		log.debug("Item request received for: " + newItem);

		Item i2 = sampleService.getClassifiedItem(newItem);

		return i2;
	}
	
	@RequestMapping(value = "/diesase", method = RequestMethod.GET, produces = "application/json")
	public List<Disease> getDissease() {
		
		List<Symptom> symptoms = new ArrayList<Symptom>();
		Symptom symptom = new Symptom();
		symptom.setSymptomType(SymptomType.GLAVOBOLJA);
		symptoms.add(symptom);
		Symptom symptom1 = new Symptom();
		symptom1.setSymptomType(SymptomType.KIJANJE);
		symptoms.add(symptom1);
		Symptom symptom2 = new Symptom();
		symptom2.setSymptomType(SymptomType.CURENJE_IZ_NOSA);
		symptoms.add(symptom2);
		Symptom symptom3 = new Symptom();
		symptom3.setSymptomType(SymptomType.KASLJANJE);
		symptoms.add(symptom3);
		Symptom symptom4 = new Symptom();
		symptom4.setSymptomType(SymptomType.BOL_U_GRLU);
		symptoms.add(symptom4);
		
		return diseaseService.getMostLickelyDiseases(symptoms);
	}
}
