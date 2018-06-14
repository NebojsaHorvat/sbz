package drools.spring.example.prescription;

import java.util.List;

import drools.spring.example.users.User;

public interface PrescriptionService {

	Prescription findOne(Long id);
	
	List<Prescription> findAll();
	
	Prescription add(Prescription prescription);

	Prescription addPrescription(Long patientId, 
			Long medicineId, Long diseaseId,User user);
	
}
