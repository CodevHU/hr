package hu.webuni.hr.domi;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.webuni.hr.domi.dto.EmployeeDto;
import hu.webuni.hr.domi.model.Employee;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTestIT {
	
	private static final String BASE_URI = "/api/employees";
	
	@Autowired
	WebTestClient webTestClient;
	
	@Test
	void testThanPutEmployeeIsChanged() throws Exception {
		
		List<EmployeeDto> employeesBefore = getAllEmployees();
		
		EmployeeDto updatedEmployee = employeesBefore.get(0);
		long id = updatedEmployee.getIdentifier();
		
		updatedEmployee.setName("Módosítva");
		
		
		putEmployee(id, updatedEmployee);
		
		List<EmployeeDto> employeesAfter = getAllEmployees();
		
		assertThat(employeesAfter.get((int)id).equals(updatedEmployee));
		
	}
	
	@Test
	void testThanPostEmployeeIsChanged() throws Exception {
		
		List<EmployeeDto> employeesBefore = getAllEmployees();
		EmployeeDto newEmploye = new EmployeeDto(10, "Kóst Elemér", "CEO", 2000000, LocalDateTime.of(2000, 1, 14, 10, 34));
		
		postEmployee(newEmploye);
		
		List<EmployeeDto> employeesAfter = getAllEmployees();
		
		assertThat(employeesAfter.subList(0, employeesBefore.size())).containsExactlyElementsOf(employeesBefore);
		assertThat(employeesAfter.get(employeesAfter.size() - 1).equals(newEmploye));
		
	}
	
	private List<EmployeeDto> getAllEmployees() {
		List<EmployeeDto> employees = 
				webTestClient
					.get()
					.uri(BASE_URI)
					.exchange()
					.expectStatus().isOk()
					.expectBodyList(EmployeeDto.class)
					.returnResult().getResponseBody();
		
		Collections.sort(employees, (a1,a2) -> Long.compare(a1.getIdentifier(), a2.getIdentifier()));
		
		return employees;	
		
	}
	
	private EmployeeDto putEmployee(long id, EmployeeDto employee) {
		EmployeeDto updatedEmployee = webTestClient
			.put()
			.uri(BASE_URI + "/" + id)
			.bodyValue(employee)
			.exchange()
			.expectStatus().isOk()
			.expectBody(EmployeeDto.class)
			.returnResult().getResponseBody();
	
		return updatedEmployee;
	}
	
	private void postEmployee(EmployeeDto employee) {
		webTestClient
			.post()
			.uri(BASE_URI)
			.bodyValue(employee)
			.exchange()
			.expectStatus().isOk()
			.expectBody(EmployeeDto.class)
			.returnResult().getResponseBody();
	}

}