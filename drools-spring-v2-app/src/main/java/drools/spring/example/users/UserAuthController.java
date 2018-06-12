package drools.spring.example.users;

import java.net.UnknownHostException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-auth")
public class UserAuthController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserConverter userConverter;
	@Autowired
	private HttpSession session;


	@PostMapping
	public ResponseEntity<User> register(@RequestBody @Valid User user) 
			throws  InterruptedException, UnknownHostException {
		User registered = userService.register(user);
		if(registered == null)
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		

		return new ResponseEntity<User>(registered, HttpStatus.OK);
	}
	
	@GetMapping("/confirm/{token}")
	public ResponseEntity<User> confirm(@PathVariable String token) {
		User user = userService.confirm(token);
		if(user == null)
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<User> logIn(@RequestBody @Valid User user) {
		User newUser = userService.logIn(user);
		if(newUser == null)
			return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
		
		session.setAttribute("user", newUser);
		return new ResponseEntity<User>(newUser, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<User> logOut() {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		
		session.invalidate();
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<User> getUser() {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	
}
