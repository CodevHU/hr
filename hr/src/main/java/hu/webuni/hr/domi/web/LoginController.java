package hu.webuni.hr.domi.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.domi.dto.LoginDto;
import hu.webuni.hr.domi.security.EmployeeUser;
import hu.webuni.hr.domi.security.JWTService;

@RestController
public class LoginController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JWTService jwtService;
	
	@PostMapping("/api/login")
	public String login(@RequestBody @Valid LoginDto loginDto) {
		
		String username = loginDto.getUsername();
		String password = loginDto.getPassword();
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		
		return jwtService.createToken( (EmployeeUser) authentication.getPrincipal());
		
	}
	
}
