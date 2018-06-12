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
	
	private DiseaseType diseaseType;
	
	private long timeStamp;
	
	private double chance;
	
	private Long numberOfSymptoms;

	private int diseaseGroup;
	
	public Disease() {}
	
	
	
	

	public int getDiseaseGroup() {
		return diseaseGroup;
	}

	public void setDiseaseGroup(int diseaseGroup) {
		this.diseaseGroup = diseaseGroup;
	}

	public Long getNumberOfSymptoms() {
		return numberOfSymptoms;
	}

	public void setNumberOfSymptoms(Long numberOfSymptoms) {
		this.numberOfSymptoms = numberOfSymptoms;
	}

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

	public DiseaseType getDiseaseType() {
		return diseaseType;
	}

	public void setDiseaseType(DiseaseType diseaseType) {
		this.diseaseType = diseaseType;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public double getChance() {
		return chance;
	}

	public void setChance(double chance) {
		this.chance = chance;
	}
	
	
}
