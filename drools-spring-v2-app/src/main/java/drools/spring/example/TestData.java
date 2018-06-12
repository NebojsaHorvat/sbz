package drools.spring.example;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import drools.spring.example.patient.Patient;
import drools.spring.example.patient.PatientService;
import drools.spring.example.users.User;
import drools.spring.example.users.UserService;
import drools.spring.example.users.UserType;

@Component
public class TestData {

	@Autowired
	private PatientService patientService;
	
	@Autowired
	private UserService userService;
	
	@PostConstruct
	private void init() {
		
		User user1 = new User("ddd@ddd", "qweqwe", "Chewbacca", "Chewbacca", "Kashyyyk", null);
		user1.setUserType(UserType.DOCTOR);
		userService.register(user1);
		
		User user2 = new User("eee@eee", "qweqwe", "Richard", "Stallman", "New York", null);
		user2.setUserType(UserType.DOCTOR);
		userService.register(user2);
		
		User user3 = new User("admin@admin", "qweqwe", "Han", "Solo", "Corellia", null);
		user3.setUserType(UserType.SYSADMIN);
		userService.register(user3);
		
		Patient p1 = new Patient("Nebojsa","Horvat");
		patientService.add(p1);
		Patient p2 = new Patient("Borislav","Puzic");
		patientService.add(p2);
		
	}
}
