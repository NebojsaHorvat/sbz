package drools.spring.example.patient;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import drools.spring.example.medicine.Medicine;

@Entity
public class Patient {

	@Id
	@GeneratedValue
	private Long id;
	
	@Version
	private Long version;
	
	private String name;
	
	private String surname;

	private String alergicSubstances;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "PATIENT_MEDICINE",
            joinColumns = @JoinColumn(name = "PATIENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "MEDICINE_ID", referencedColumnName = "ID"))
    private List<Medicine> alergicMedicines;
	
	public Patient () {}
	
	
	public Patient(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
}
