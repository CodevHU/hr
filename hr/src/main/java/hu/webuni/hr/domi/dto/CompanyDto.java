package hu.webuni.hr.domi.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

import hu.webuni.hr.domi.model.Employee;
import hu.webuni.hr.domi.model.Company.CompanyType;

public class CompanyDto {
	
	private long id;
	private String registrationNumber;
	private String name;
	private String address;
	private CompanyType companyType;
	
	@OneToMany(mappedBy = "company")
	List<Employee> employees = new ArrayList<>();
	
	
	public CompanyDto() {		
	}
	
	public CompanyDto(long id, String registrationNumber, String name, String address, CompanyType companyType, List<Employee> employees) {
		this.id = id;
		this.registrationNumber = registrationNumber;
		this.name = name;
		this.address = address;
		this.employees = employees;
		this.companyType = companyType;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}
	
	
	
}
