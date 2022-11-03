package hu.webuni.hr.domi.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.domi.dto.CompanyDto;
import hu.webuni.hr.domi.dto.EmployeeDto;
import hu.webuni.hr.domi.mapper.CompanyMapper;
import hu.webuni.hr.domi.mapper.EmployeeMapper;
import hu.webuni.hr.domi.model.Company;
import hu.webuni.hr.domi.service.CompanyService;
import hu.webuni.hr.domi.service.EmployeeService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
	
//	@Autowired
//	AbstractEmployeeService abstractemployeeService;

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	EmployeeService employeeService;

	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired
	private EmployeeMapper employeeMapper;

	@GetMapping
	public List<CompanyDto> getCompanies(@RequestParam Optional<Boolean> full) {

		if (full.orElse(false)) {
			return companyMapper.companyToDtos(companyService.getCompaniesWithoutEmployees());
		} else {
			return companyMapper.companyToDtos(companyService.getCompaniesWithEmployees());
		}
	}

	@GetMapping("/{id}")
	public CompanyDto getCompanyById(@PathVariable int id) {
		
		Company company = companyService.getById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		return companyMapper.companyToDto(company);
		
	}

	@PostMapping
	public CompanyDto addCompany(@RequestBody CompanyDto company) {
		
		companyService.saveCompany(companyMapper.dtoToCompany(company));
		return company;
	}

	@PutMapping("/{id}")
	public CompanyDto updateCompany(@PathVariable long id, @RequestBody CompanyDto company) {

		companyService.updateCompany(id, companyMapper.dtoToCompany(company));
		
		return company;
	}

	@DeleteMapping("/{id}")
	public boolean deleteCompany(@PathVariable long id) {

		companyService.deleteCompany(id);
		return true;

	}

	@PostMapping("/{id}/employees")
	public CompanyDto addEmployeeToCompany(@RequestBody EmployeeDto employee, @PathVariable int id) {

		Company company = companyService.addEmployeeToCompany(id,  employeeMapper.employeeDtoToEmployee(employee));
		
		return companyMapper.companyToDto(company);

	}

	@DeleteMapping("/{id}/employees/{eid}")
	public boolean deleteEmployee(@PathVariable int id, @PathVariable int eid) {

		companyService.deleteEmployeeFromCompany(id,eid);
		return true;

	}
	
	@PutMapping("/{id}/employees")
	public CompanyDto changeAllEmployeeInCompany(@PathVariable int id, @RequestBody List<EmployeeDto> employees){
		
		Company company = companyService.updateAllEmployeeToCompany(id, employeeMapper.employeeDtosToEmployee(employees));
		return companyMapper.companyToDto(company);
		
	}


}
