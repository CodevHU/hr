package hu.webuni.hr.domi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration.builder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import hu.webuni.hr.domi.model.Company;
import hu.webuni.hr.domi.model.Employee;
import hu.webuni.hr.domi.repository.CompanyRepository;
import hu.webuni.hr.domi.repository.EmployeeRepository;
import hu.webuni.hr.domi.service.CompanyService;

@SpringBootTest
@AutoConfigureTestDatabase
public class CompanyControllerTestIT {
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	Comparator<LocalDateTime> truncateSeconds = (a, exp) ->
    a.isAfter(exp.truncatedTo(ChronoUnit.SECONDS)) ? 0 : 1;
	
	@BeforeEach
	public void init() {
		employeeRepository.deleteAll();
		companyRepository.deleteAll();
	}


	@Test
	void testAddEmployeeAtCompany() throws Exception {
	
		Company company = companyRepository.save(new Company(0L,"Kiss és Társa Kft.","32222454-2-45","2310 Szigetszentmiklós, Szent Miklós útja 12.",null));
		Employee employee = employeeRepository.save(new Employee(0L, "Kiss István",null, 570000,LocalDateTime.now().minusHours(1),null));
		
		companyService.addEmployeeToCompany(company.getId(),employee);
		
		assertThat(employeeRepository.findById(employee.getId())).isNotEmpty();
		assertThat(companyRepository.findById(company.getId())).isNotEmpty();
		assertThat(employee.getCompany().getId()).isEqualTo(company.getId());
		
	}
	
	@Test
	void testDeleteEmployeeFromCompany() throws Exception {
		Company company = companyRepository.save(new Company(0L,"Kiss és Társa Kft.","32222454-2-45","2310 Szigetszentmiklós, Szent Miklós útja 12.",null));
		Employee employee = employeeRepository.save(new Employee(0L, "Kiss István",null, 570000,LocalDateTime.now().minusHours(1),null));
		
		companyService.addEmployeeToCompany(company.getId(),employee);
		
		assertThat(employeeRepository.findById(employee.getId())).isNotEmpty();
		assertThat(companyRepository.findById(company.getId())).isNotEmpty();
		assertThat(employee.getCompany().getId()).isEqualTo(company.getId());
		
		companyService.deleteEmployeeFromCompany(company.getId(), employee.getId());
		
		assertThat(employeeRepository.findById(employee.getId()).get().getCompany()).isNull();
		
	}
	
	@Test
	void testUpdateAllEmployeesAtCompany() throws Exception {
	
		Company company = companyRepository.save(new Company(0L,"Kiss és Társa Kft.","32222454-2-45","2310 Szigetszentmiklós, Szent Miklós útja 12.",null));
		Employee employee1 = employeeRepository.save(new Employee(0L, "Kiss István",null, 570000,LocalDateTime.now().minusHours(1),null));
		Employee employee2 = employeeRepository.save(new Employee(0L, "László Attila",null, 520000,LocalDateTime.now().minusHours(2),null));
		Employee employee3 = employeeRepository.save(new Employee(0L, "Elke István",null, 470000,LocalDateTime.now().minusHours(3),null));
		Employee employee4 = employeeRepository.save(new Employee(0L, "Nagy László",null, 270000,LocalDateTime.now().minusHours(4),null));
		
		
		List<Employee> employees = new ArrayList<>();
		
		employees.add(employee1);
		employees.add(employee2);
		
		Company updatedCompany1 = companyService.updateAllEmployeeToCompany(company.getId(), employees);
		
		employees.clear();
		
		employees.add(employee3);
		employees.add(employee4);
		
		Company updatedCompany2 = companyService.updateAllEmployeeToCompany(company.getId(), employees);
		
		RecursiveComparisonConfiguration configuration = builder().withIgnoreAllOverriddenEquals(true)
                .withComparatorForType(truncateSeconds, LocalDateTime.class)
                .build();
		
		assertThat(companyRepository.findById(company.getId()).get().getEmployees())
			.usingRecursiveFieldByFieldElementComparator(configuration)
			.containsExactlyElementsOf(employees);
		
	}
	
	
}
