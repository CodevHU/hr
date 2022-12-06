package hu.webuni.hr.domi;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.webuni.hr.domi.model.Employee;
import hu.webuni.hr.domi.repository.CompanyRepository;
import hu.webuni.hr.domi.repository.EmployeeRepository;
import hu.webuni.hr.domi.repository.LeaveRepository;
import hu.webuni.hr.domi.security.EmployeeUser;
import hu.webuni.hr.domi.security.JWTService;

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
	
	@Autowired
	JWTService jwtService;
	 
	private Employee createdBy;
	private Employee superior;
	
	private String token;
	
	@BeforeEach
	public void init() {
		leaveRepository.deleteAll();
		employeeRepository.deleteAll();
		companyRepository.deleteAll();
		
		this.superior = employeeRepository.save(new Employee(0L, "Felettes István", null, 0, LocalDateTime.now().minusDays(5)));
		
		Employee emp = new Employee(0L, "Kiss Elemér2", null, 0, LocalDateTime.now().minusDays(2));
		emp.setUsername(USERNAME);
		emp.setPassword(passwordEncoder.encode(PASS));
		emp.setSuperior(superior);
		
		this.createdBy = employeeRepository.save(emp);
		
		
//		this.token = getToken();
	}
	
	@Test
	void testWhenGetAllHollidayRequestsWithoutLogin() throws Exception {
		assertThat(
				webTestClient
					.get()
					.uri(BASE_URI)
					.exchange()
					.expectStatus()
					.isEqualTo(HttpStatus.FORBIDDEN));
		
	}
	
	@Test
	void testWhenGetAllHollidayRequestsWithLogin() throws Exception {
		
		this.token = getToken();
		
		assertThat(
				webTestClient
					.get()
					.uri(BASE_URI)
					.headers(h -> h.setBearerAuth(token))
					.exchange()
					.expectStatus()
					.isOk());
		
	}
	
	private String getToken() {
		return jwtService.createToken(new EmployeeUser(USERNAME, PASS, Arrays.asList(new SimpleGrantedAuthority("USER")), createdBy));
	}

}
