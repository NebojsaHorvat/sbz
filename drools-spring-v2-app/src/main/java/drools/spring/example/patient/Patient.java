package drools.spring.example.patient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Patient {

	@Id
	@GeneratedValue
	private Long id;
	
	@Version
	private Long version;
	
	private String name;
	
	private String surname;
	
	
}
