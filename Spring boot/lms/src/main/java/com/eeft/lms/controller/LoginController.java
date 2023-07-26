package com.eeft.lms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eeft.lms.crpto.PasswordCrypt;
import com.eeft.lms.dao.UserRepository;
import com.eeft.lms.object.LeaveRequest;
import com.eeft.lms.object.Login;
import com.eeft.lms.service.LMSService;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/lms")
public class LoginController {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private LMSService lmsService;

	@SuppressWarnings("null")
	@PostMapping("/login")
	public ResponseEntity<Login> login(@RequestBody Map<String, String> credentials)
	{
		String username = credentials.get("username");
        String password = credentials.get("password");

        Login user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(PasswordCrypt.encrypt(password))) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@SuppressWarnings("null")
	@PostMapping("/logout")
	  public ResponseEntity<Login> logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        System.out.println("Logged out successfully !!");
        return ResponseEntity.status(HttpStatus.OK).build();
	}     
        
	@GetMapping("/userDetails/{emp_id}")
	public ResponseEntity<Login> userDetail(@PathVariable ("emp_id") String emp_Id)
	{
		
        Login user = userRepository.userDetails(emp_Id);


        return ResponseEntity.ok(user);
	}
	

}