package drools.spring.example.disease;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import drools.spring.example.patient.Patient;
import drools.spring.example.symptom.SymptomType;

@Entity
public class Disease {

	@Id
	@GeneratedValue
	private Long id;
	
	@Version
	private Long version;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Patient patient;
	
	private SymptomType symptomType;
	
	private long timeStamp;
}
