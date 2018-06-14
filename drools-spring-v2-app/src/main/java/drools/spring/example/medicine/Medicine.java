package drools.spring.example.medicine;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import drools.spring.example.disease.Disease;
import drools.spring.example.patient.Patient;
import drools.spring.example.users.User;

@Entity
public class Medicine {

	@Id
	@GeneratedValue
	private Long id;
	
	@Version
	private Long version;
	
	private MedicineType medicineType;
	
	private String substances;
	
	public Medicine () {}

	public Medicine(Patient patient, Disease disease, User user, MedicineType medicineType, String substances) {
		super();
		this.medicineType = medicineType;
		this.substances = substances;
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

	public MedicineType getMedicineType() {
		return medicineType;
	}

	public void setMedicineType(MedicineType medicineType) {
		this.medicineType = medicineType;
	}

	public String getSubstances() {
		return substances;
	}

	public void setSubstances(String substances) {
		this.substances = substances;
	}
	
	
	
	
}
