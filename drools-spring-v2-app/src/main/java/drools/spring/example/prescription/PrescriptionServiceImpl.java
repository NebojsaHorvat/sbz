package drools.spring.example.prescription;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import drools.spring.example.disease.Disease;
import drools.spring.example.disease.DiseaseService;
import drools.spring.example.medicine.Medicine;
import drools.spring.example.medicine.MedicineService;
import drools.spring.example.patient.Patient;
import drools.spring.example.patient.PatientService;
import drools.spring.example.users.User;

@Transactional(readOnly = true)
@Service
public class PrescriptionServiceImpl implements PrescriptionService{

	@Autowired 
	private PrescriptionRepository prescriptionRepository;
	
	@Autowired
	private MedicineService medicineService;

	@Autowired
	private PatientService patientService;
	
	@Autowired
	private DiseaseService diseaseService;
	
	public Prescription findOne(Long id) {
		return prescriptionRepository.findById(id).get();
	}

	public List<Prescription> findAll() {
		return prescriptionRepository.findAll();
	}
	@Transactional(readOnly = false)
	public Prescription add(Prescription prescription) {
		return prescriptionRepository.save(prescription);
	}
	@Transactional(readOnly = false)
	public Prescription addPrescription(Long patientId,
			Long medicineId, Long diseaseId, User user) {
		
		Patient patient= patientService.findOne(patientId);
		if(patient == null)
			return null;
		Medicine medicine = medicineService.findOne(medicineId);
		if(medicine == null)
			return null;
		Disease disease = diseaseService.findOne(diseaseId);
		if(disease == null)
			return null;
		
		Prescription p = new Prescription(patient, disease, user, medicine);
		p = add(p);
		
		return p;
	}
	
	

}
