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
	
	public Symptom() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public SymptomType getSymptomType() {
		return symptomType;
	}

	public void setSymptomType(SymptomType symptomType) {
		this.symptomType = symptomType;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
