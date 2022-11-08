package hu.webuni.hr.domi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Company {
	
//	public enum CompanyType {BT,KFT,ZRT,NYRT}
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	private String registrationNumber;
	private String name;
	private String address;
	
	@ManyToOne
	private CompanyType companyType;
	
	@OneToMany(mappedBy = "company")
	List<Employee> employees = new ArrayList<>();
	
	public Company() {		
	}
	

	
	public Company(long id, String registrationNumber, String name, String address,
			List<Employee> employees) {
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
	
	public void addEmployee(Employee employee) {
		
		if(this.employees == null)
			this.employees = new ArrayList<>();
		
		this.employees.add(employee);
		employee.setCompany(this);
	}

	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}
	
}
