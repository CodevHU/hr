package hu.webuni.hr.domi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.domi.dto.CompanyDto;
import hu.webuni.hr.domi.mapper.CompanyMapper;
import hu.webuni.hr.domi.mapper.EmployeeMapper;
import hu.webuni.hr.domi.model.Company;
import hu.webuni.hr.domi.model.Employee;
import hu.webuni.hr.domi.model.EmployeeSalaryAvg;
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
	
	@Autowired
	CompanyMapper companyMapper;
	
	
	public List<CompanyDto> getCompaniesWithEmployees() {
//		List<Company> employees = companyRepository.findAll();
		List<Company> employees = companyRepository.findAllWithEmployees();
		
		return companyMapper.companiesToDtos(employees);
	}
	
	public List<CompanyDto> getCompaniesWithoutEmployees() {
		List<Company> employees = companyRepository.findAll();
		
		return companyMapper.companiesToSummaryDtos(employees);
	}
	
	public Optional<Company> getById(long id) {
		
		return companyRepository.findById(id);
		
	}
	
	public Company getCompanyWithEmployees(long id) {
		// TODO Auto-generated method stub
		return companyRepository.findByIdWithEmployee(id);
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
	public Company addEmployeeToCompany(long companyId, Employee employee) {
		
		Company company = companyRepository.findById(companyId).get();
		
		company.addEmployee(employee);
		employeeRepository.save(employee);
		
		return company;
		
	}
	
	@Transactional
	public Company deleteEmployeeFromCompany(long id, long eid) {
		
		Company company = companyRepository.findById(id).get();
		Employee employee = employeeRepository.findById(eid).get();
		
		employee.setCompany(null);
		company.getEmployees().remove(employee);
		
		return company;
	
	}
	
	@Transactional
	public Company updateAllEmployeeToCompany(long id, List<Employee> employees) {
		
		Company company = companyRepository.findById(id).get();
		
		company.getEmployees()
			.forEach(em -> {
				em.setCompany(null);
//				employeeRepository.save(em);
			});
		
		company.getEmployees().clear();
		
		employees.forEach(em -> {
			company.addEmployee(em);
			employeeRepository.save(em);
		});
		
		return company;
		
	}
	
	public List<EmployeeSalaryAvg> getAvg(long companyId) {
		List<EmployeeSalaryAvg> epmployeeSalaryAvg = companyRepository.getAvgByInterface(companyId);
		return epmployeeSalaryAvg;
	}
	
	public List<Company> getCompaniesHhereTheSalaryOfTheEmployeesIsGreaterThanParam(int salary){

		return companyRepository.findAllByEmployeesPayGreaterThan(salary);
	}
	
	public List<Company> getCompaniesWhereTheEmployeesCountIsGreaterThanParam(Long employeeCount) {
		return companyRepository.findCompaniesWhereTheEmployeesCountIsGreaterThanParam(employeeCount);
	}


	
}
