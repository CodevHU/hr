package hu.webuni.hr.domi;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Base64Utils;

import hu.webuni.hr.domi.model.Employee;
import hu.webuni.hr.domi.repository.CompanyRepository;
import hu.webuni.hr.domi.repository.EmployeeRepository;
import hu.webuni.hr.domi.repository.LeaveRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class LeaveControllerTestIT {
	
	private static final String PASS = "pass";

	private static final String USERNAME = "test_admin";

	private static final String BASE_URI = "/api/leaves";

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	LeaveRepository leaveRepository;
	
	@Autowired
	WebTestClient webTestClient;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	 
	private Employee createdBy;
	private Employee superior;
	
	@BeforeEach
	public void init() {
		leaveRepository.deleteAll();
		employeeRepository.deleteAll();
		companyRepository.deleteAll();
		
		Employee emp = new Employee(0L, "Kiss Elemér2", null, 0, LocalDateTime.now().minusDays(2));
		emp.setUsername(USERNAME);
		emp.setPassword(passwordEncoder.encode(PASS));
		
		this.createdBy = employeeRepository.save(emp);
		this.superior = employeeRepository.save(new Employee(0L, "Felettes István", null, 0, LocalDateTime.now().minusDays(5)));
	}
	
	@Test
	void testWhenGetAllHollidayRequestsWithoutLogin() throws Exception {
		
		assertThat(
				webTestClient
					.get()
					.uri(BASE_URI)
					.exchange()
					.expectStatus()
					.isEqualTo(HttpStatus.UNAUTHORIZED));
		
	}
	
	@Test
	void testWhenGetAllHollidayRequestsWithLogin() throws Exception {
		
		assertThat(
				webTestClient
					.get()
					.uri(BASE_URI)
					.header("Authorization", "Basic " + Base64Utils
		                    .encodeToString((USERNAME + ":" + PASS).getBytes()))
					.exchange()
					.expectStatus()
					.isOk());
		
	}

}
