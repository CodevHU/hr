package hu.webuni.hr.domi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.domi.dto.CompanyDto;
import hu.webuni.hr.domi.dto.EmployeeDto;
import hu.webuni.hr.domi.model.Company;

@Service
public class CompanyService {

	
	Map<Long, Company> allCompany = new HashMap<>();

	{

		allCompany.put(1L, new Company(1, "01-06-016428", "TÁRSALGÓ BT", "1213 Budapest, Hollandi út 107/a.",
				new ArrayList<>(List.of(
						new EmployeeDto(1, "Kóst Elek", "CEO", 1000000, LocalDateTime.of(2010, 1, 14, 10, 34)),
						new EmployeeDto(2, "Lapos Elemér", "Coordinator", 200000,
								LocalDateTime.of(2014, 1, 14, 10, 34)),
						new EmployeeDto(3, "Lusta Gyula", "Manager", 300000, LocalDateTime.of(2018, 1, 14, 10, 34)),
						new EmployeeDto(4, "Kiss Zoltán", "Project Manager", 800000,
								LocalDateTime.of(2020, 1, 14, 10, 34))))));

		allCompany.put(2L,
				new Company(2, "01-06-613027", "CAC BT", "1222 Budapest, Lugosi u. 1.", new ArrayList<>(List.of(
						new EmployeeDto(1, "Fix László", "CEO", 2000000, LocalDateTime.of(2000, 1, 14, 10, 34)),
						new EmployeeDto(2, "Nagy István", "CTO", 800000, LocalDateTime.of(2010, 1, 14, 10, 34)),
						new EmployeeDto(3, "Kis Elek", "Operátor", 100000, LocalDateTime.of(2020, 1, 14, 10, 34))))));

		allCompany.put(3L, new Company(3, "01-06-748293", "TÁRSASVÁR Bt.", "1161 Budapest, Csömöri út 122.",
				new ArrayList<>(List.of(
						new EmployeeDto(1, "Kis Etelka", "CEO", 1000000, LocalDateTime.of(1998, 1, 14, 10, 34)),
						new EmployeeDto(2, "Nagy Béla", "Eladó", 200000, LocalDateTime.of(2010, 1, 14, 10, 34))))));

	}
	
	
	public List<Company> getCompaniesWithoutEmployees() {
		return new ArrayList<>(allCompany.values());
	}
	
	public List<Company> getCompaniesWithEmployees() {
		
		List<Company> companiesWithoutEmployees = allCompany.values().stream().map(c -> createCompanyWithoutEmployees(c)).collect(Collectors.toList());
				
		return companiesWithoutEmployees;
	}
	
	public Company getById(long id) {
		
		if(allCompany.containsKey(id)) {
			return allCompany.get(id);
		}
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	public Company saveCompany(Company company) {
		allCompany.put(company.getId(), company);
		
		return company;
	}
	
	public Company updateCompany(long id, Company company) {
		
		if(allCompany.containsKey(id)) {
			allCompany.put(id, company);
			return allCompany.get(id);
		}
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	public void deleteCompany(long id) {
		
		if (allCompany.containsKey(id)) {
			allCompany.remove(id);
		}

		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
	}
	
	public Company addEmployeeToCompany(long companyId, EmployeeDto employee) {
		
		if(!allCompany.containsKey(companyId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		List<EmployeeDto> employees = allCompany.get(companyId).getEmployees();
		employees.add(employee);

		allCompany.get(companyId).setEmployees(employees);
		
		return allCompany.get(companyId);
		
	}
	
	public void deleteEmployeeFromCompany(long id, long eid) {
		
		if(!allCompany.containsKey(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		if(!allCompany.get(id).getEmployees().removeIf(e -> e.getIdentifier() == eid)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	
	}
	
	public Company updateAllEmployeeToCompany(long id, List<EmployeeDto> employees) {
		
		if(!allCompany.containsKey(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		allCompany.get(id).setEmployees(employees);
		
		return allCompany.get(id);
	}
	
	
	
	
	
	private Company createCompanyWithoutEmployees(Company c) {
		return new Company(c.getId(), c.getRegistrationNumber(), c.getName(), c.getAddress(), null);
	}

	
	

	

	
	
}
