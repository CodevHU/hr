package hu.webuni.hr.domi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.domi.dto.EmployeeDto;
import hu.webuni.hr.domi.mapper.EmployeeMapper;
import hu.webuni.hr.domi.model.Company;
import hu.webuni.hr.domi.model.Employee;
import hu.webuni.hr.domi.repository.CompanyRepository;
import hu.webuni.hr.domi.repository.EmployeeRepository;

@Service
public class CompanyService {

	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	
	public List<Company> getCompaniesWithoutEmployees() {
		return companyRepository.findAll();
	}
	
	public List<Company> getCompaniesWithEmployees() {
		
		List<Company> companiesWithoutEmployees = companyRepository.findAll().stream().map(c -> createCompanyWithoutEmployees(c)).collect(Collectors.toList());
				
		return companiesWithoutEmployees;
	}
	
	public Optional<Company> getById(long id) {
		
		return companyRepository.findById(id);
		
	}
	
	@Transactional
	public Company saveCompany(Company company) {
		
//		employeeRepository.findAllById(
//				company.getEmployees()
//					.stream()
//					.map(Employee::getId)
//					.collect(Collectors.toList())
//					);
		
		company.getEmployees().stream().peek(f -> {
			Optional<Employee> employee = employeeRepository.findById(f.getId());
			employee.get().setCompany(company);
			employeeRepository.save(employee.get());
			
		}).collect(Collectors.toList());
		
		return companyRepository.save(company);
	}
	
	@Transactional
	public Company updateCompany(long id, Company company) {
		
		company.setId(id);
		if(companyRepository.existsById(company.getId())) {
			
			company.getEmployees().stream().peek(f -> {
				Optional<Employee> employee = employeeRepository.findById(f.getId());
				employee.get().setCompany(company);
				employeeRepository.save(employee.get());
				
			}).collect(Collectors.toList());
			
			return companyRepository.save(company); 
			}
		else 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
	}
	
	@Transactional
	public void deleteCompany(long id) {
		
		Optional<Company> company = companyRepository.findById(id);
		
		if (!company.isEmpty()) {
			companyRepository.deleteById(id);
		}

		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	@Transactional
	public Company addEmployeeToCompany(long companyId, EmployeeDto employeeDto) {
		
		Optional<Company> company = companyRepository.findById(companyId);
		Optional<Employee> employee = employeeRepository.findById(employeeDto.getId());
		
		
		if (!company.isEmpty() && !employee.isEmpty()) {
			
			
			List<Employee> employees = company.get().getEmployees();
			employee.get().setCompany(company.get());
			employees.add(employee.get());
			
			
			company.get().setEmployees(employees);
			
			return companyRepository.save(company.get());
			
			
		} else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
	}
	
	@Transactional
	public void deleteEmployeeFromCompany(long id, long eid) {
		
		Optional<Company> company = companyRepository.findById(id);
		Optional<Employee> employee = employeeRepository.findById(eid);
		
		if (company.isPresent() && employee.isPresent()) {
			
			if(company.get().getEmployees().removeIf(e -> e.getId() == eid)) {
				employee.get().setCompany(null);
				employeeRepository.save(employee.get());
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	
	}
	
	@Transactional
	public Company updateAllEmployeeToCompany(long id, List<EmployeeDto> employeesDto) {
		
		Optional<Company> company = companyRepository.findById(id);
		
		if (company.isPresent()) {
		
			company.get().getEmployees().stream()
				.peek(f -> {
					Optional<Employee> employee = employeeRepository.findById(f.getId());
					employee.get().setCompany(null);
					employeeRepository.save(employee.get());
					})
				.collect(Collectors.toList());
			
			company.get().setEmployees(new ArrayList<Employee>());
			
			List<Employee> employeesData = employeeMapper.employeeDtosToEmployee(employeesDto);
			
			employeesData.stream().peek(f -> {
				Optional<Employee> employee = employeeRepository.findById(f.getId());
				employee.get().setCompany(company.get());
				employeeRepository.save(employee.get());
				
				company.get().getEmployees().add(employee.get());
			}).collect(Collectors.toList());
			
			return companyRepository.save(company.get());
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
	
	private Company createCompanyWithoutEmployees(Company c) {
		return new Company(c.getId(), c.getRegistrationNumber(), c.getName(), c.getAddress(), null);
	}

	
	

	

	
	
}
