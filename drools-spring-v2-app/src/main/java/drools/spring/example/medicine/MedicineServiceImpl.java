package drools.spring.example.medicine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import drools.spring.example.patient.Patient;

@Transactional(readOnly = true)
@Service
public class MedicineServiceImpl implements MedicineService{

	@Autowired
	private MedicineRepository medicineRepository;

	public Medicine findOne(Long id) {
		return medicineRepository.findById(id).get();
	}

	public List<Medicine> findAll() {
		return medicineRepository.findAll();
	}

	@Transactional(readOnly = false)
	public Medicine add(Medicine medicine) {
		return medicineRepository.save(medicine);
	}

	
}
