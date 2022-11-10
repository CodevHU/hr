package hu.webuni.hr.domi.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.webuni.hr.domi.dto.CompanyDto;
import hu.webuni.hr.domi.dto.EmployeeDto;
import hu.webuni.hr.domi.model.Company;
import hu.webuni.hr.domi.model.Employee;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
	
	List<CompanyDto> companyToDtos(List<Company> all);

	@Mapping(target = "employees.company", ignore = true)
	@Mapping(target = "employees.position.employees", ignore = true)
	CompanyDto companyToDto(Company byId);
	
	@Mapping(target = "employees", ignore = true)
	Company dtoToCompany(CompanyDto company);
	
	@Mapping(target = "employees", ignore = true)
	@Named("summary")
	CompanyDto companyToSummaryDto(Company company);
	
	List<CompanyDto> companiesToDtos(List<Company> company);
	
	@IterableMapping(qualifiedByName = "summary")
	List<CompanyDto> companiesToSummaryDtos(List<Company> company);
	
	@Mapping(target = "company", ignore = true)
	@Mapping(target = "position.employees", ignore = true)
	EmployeeDto employeeToDto(Employee employee);

}
