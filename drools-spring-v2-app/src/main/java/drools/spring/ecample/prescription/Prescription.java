package drools.spring.ecample.prescription;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import drools.spring.example.disease.Disease;
import drools.spring.example.medicine.Medicine;
import drools.spring.example.patient.Patient;
import drools.spring.example.users.User;

public class Prescription {

	@ManyToOne(fetch = FetchType.EAGER)
	private Patient patient;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Disease disease;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Medicine medicine;
}
