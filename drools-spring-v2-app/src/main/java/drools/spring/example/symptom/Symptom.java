package drools.spring.example.symptom;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import drools.spring.example.patient.Patient;

@Entity
public class Symptom {

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
