package drools.spring.example.patient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class PatientServiceImpl implements PatientService{

	@Autowired
	private PatientRepository patientRepository;

	public Patient findOne(Long id) {
		return patientRepository.findOne(id);
	}

	public List<Patient> findAll() {
		return patientRepository.findAll();
	}

	@Transactional(readOnly = false)
	public Patient add(Patient patient) {
		return patientRepository.save(patient);
	}

	@Transactional(readOnly = false)
	public Patient edit(Long id, Patient patent) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
