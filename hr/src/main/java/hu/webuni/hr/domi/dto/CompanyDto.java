package hu.webuni.hr.domi.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import hu.webuni.hr.domi.model.CompanyType;

public class CompanyDto {
	
	private long id;
	private String registrationNumber;
	private String name;
	private String address;
	
	@OneToMany(mappedBy = "company")
	List<EmployeeDto> employees = new ArrayList<>();
	
	@ManyToOne
	private CompanyType companyType;
	
	
	public CompanyDto() {		
	}
	
	public CompanyDto(long id, String registrationNumber, String name, String address, List<EmployeeDto> employees) {
		this.id = id;
		this.registrationNumber = registrationNumber;
		this.name = name;
		this.address = address;
		this.employees = employees;
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

	public List<EmployeeDto> getEmployees() {
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

	public void setEmployees(List<EmployeeDto> employees) {
		this.employees = employees;
	}

	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	@Override
	public String toString() {
		return "CompanyDto [id=" + id + ", registrationNumber=" + registrationNumber + ", name=" + name + ", address="
				+ address + ", employees=" + employees + ", companyType=" + companyType + "]";
	}
	
	

	

	
	
	
	
}
