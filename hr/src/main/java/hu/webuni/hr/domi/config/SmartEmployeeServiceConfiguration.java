package hu.webuni.hr.domi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.domi.service.EmployeeService;
import hu.webuni.hr.domi.service.SmartEmployeeService;

@Configuration
@Profile("smart")
public class SmartEmployeeServiceConfiguration {

	@Bean
	public EmployeeService employeeService() {
		return new SmartEmployeeService();
	}
	
}
