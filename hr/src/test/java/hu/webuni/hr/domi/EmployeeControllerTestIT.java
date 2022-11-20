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
import hu.webuni.hr.domi.model.Position;
import hu.webuni.hr.domi.model.Position.Qualification;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTestIT {
	
	private static final String BASE_URI = "/api/employees";
	
	@Autowired
	WebTestClient webTestClient;
	
	@Test
	void testThanPutEmployeeIsChanged() throws Exception {
		
		List<EmployeeDto> employeesBefore = getAllEmployees();
		
		EmployeeDto updatedEmployee = employeesBefore.get(0);
		long id = updatedEmployee.getId();
		
		updatedEmployee.setName("Módosítva");
		
		
		putEmployee(id, updatedEmployee);
		
		List<EmployeeDto> employeesAfter = getAllEmployees();
		
		assertThat(employeesAfter.get((int)id).equals(updatedEmployee));
		
	}
	
	@Test
	void testThanPostEmployeeIsChanged() throws Exception {
		
		EmployeeDto employee = postEmployee(new EmployeeDto(0, "Kóst Elemér", new Position("Manager",Qualification.GRADUATION),2000000, LocalDateTime.of(2000, 1, 14, 10, 34)));
		
		List<EmployeeDto> employeesAfter = getAllEmployees();
		
		assertThat(employeesAfter)
				.usingRecursiveFieldByFieldElementComparator()
				.contains(employee);
		
//		assertThat(getEmployee(employee.getId())).equals(employee);
		
	}
	
	@Test
	void testThenPostEmployeeNegativePayException() throws Exception {
		
		EmployeeDto newEmploye = new EmployeeDto(10, "Kóst Elemér", null, -2000000, LocalDateTime.of(2000, 1, 14, 10, 34));
		
		assertThat(webTestClient
				.post()
				.uri(BASE_URI)
				.bodyValue(newEmploye)
				.exchange()
				.expectStatus()
				.isBadRequest()
				);
	}
	
	@Test
	void testThenPostEmployeeWithNotPastFirstWorkDayException() throws Exception {
		
		EmployeeDto newEmploye = new EmployeeDto(10, "Kóst Elemér", null, 2000000, LocalDateTime.of(3000, 1, 14, 10, 34));
		
		assertThat(webTestClient
				.post()
				.uri(BASE_URI)
				.bodyValue(newEmploye)
				.exchange()
				.expectStatus()
				.isBadRequest()
				);
	}
	
	@Test
	void testThenPutEmployeeWithNonExistentIdException() throws Exception {
		
		List<EmployeeDto> employeesBefore = getAllEmployees();
		EmployeeDto employe = new EmployeeDto(0L, "Kóst Elemér", null, 2000000, LocalDateTime.of(2002, 1, 14, 10, 34));
		
		assertThat(webTestClient
				.put()
				.uri(BASE_URI + "/" + employeesBefore.size() + 1)
				.bodyValue(employe)
				.exchange()
				.expectStatus()
				.isNotFound()
			);
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
		
		Collections.sort(employees, (a1,a2) -> Long.compare(a1.getId(), a2.getId()));
		
		return employees;	
		
	}
	
	private EmployeeDto getEmployee(long id) {
		EmployeeDto employee = webTestClient
			.get()
			.uri(BASE_URI + "/" + id)
			.exchange()
			.expectStatus().isOk()
			.expectBody(EmployeeDto.class)
			.returnResult().getResponseBody();
	
		return employee;
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
	
	private EmployeeDto postEmployee(EmployeeDto employee) {
		return webTestClient
			.post()
			.uri(BASE_URI)
			.bodyValue(employee)
			.exchange()
			.expectStatus().isOk()
			.expectBody(EmployeeDto.class)
			.returnResult().getResponseBody();
	}

}
