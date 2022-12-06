package hu.webuni.hr.domi.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hu.webuni.hr.domi.model.Employee;
import hu.webuni.hr.domi.repository.EmployeeRepository;

@Service
public class EmployeeUserDetailsService implements UserDetailsService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Employee employee = employeeRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
		
		return new EmployeeUser(username, employee.getPassword(), Arrays.asList(new SimpleGrantedAuthority("USER")),employee);
	}

}
