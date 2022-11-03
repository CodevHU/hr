package hu.webuni.hr.domi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.domi.model.Company;
import hu.webuni.hr.domi.model.CompanyType;
import hu.webuni.hr.domi.model.Employee;
import hu.webuni.hr.domi.repository.CompanyRepository;
import hu.webuni.hr.domi.repository.EmployeeRepository;

@Service
public class InitDbService{
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Transactional
	public void clearDB() {
		employeeRepository.truncateEmployees();		
		companyRepository.truncateCompanies();		
	}
	
	@Transactional
	public void insertTestData() {
		
		List<Employee> firstCompanyEmployees = new ArrayList<>(); 
		
		firstCompanyEmployees.add(new Employee(1,"Kóst Elek","CEO",1000000, LocalDateTime.of(2010, 1, 14, 10, 34)));
		firstCompanyEmployees.add(new Employee(2,"Lapos Elemér","Coordinator",200000,LocalDateTime.of(2014, 1, 14, 10, 34)));
		firstCompanyEmployees.add(new Employee(3,"Lusta Gyula","Manager",100000,LocalDateTime.of(2018, 1, 14, 10, 34)));
		firstCompanyEmployees.add(new Employee(3,"Juhász István","Manager",300000,LocalDateTime.of(2018, 1, 14, 10, 34)));
		firstCompanyEmployees.add(new Employee(4,"Kiss Zoltán","Project Manager",800000,LocalDateTime.of(2020, 1, 14, 10, 34)));
		
		Company firstCompany = new Company(0L,"3233222-2-5","Első cég Kft.","1111 Budapest, Első utca 3.",CompanyType.KFT,firstCompanyEmployees);
		companyRepository.save(firstCompany);
		
		firstCompanyEmployees.forEach(em -> {
			em.setCompany(firstCompany);
			employeeRepository.save(em);
		});
		
		List<Employee> secondCompanyEmployees = new ArrayList<>(); 
		
		secondCompanyEmployees.add(new Employee(1,"Kovács Elek","CEO",1100000,LocalDateTime.of(2020, 1, 14, 10, 34)));
		secondCompanyEmployees.add(new Employee(2,"Varga Elemér","Coordinator",60000,LocalDateTime.of(2010, 1, 14, 10, 34)));
		secondCompanyEmployees.add(new Employee(3,"Fekete Gyula","Manager",80000,LocalDateTime.of(2016, 1, 14, 10, 34)));
		
		Company secondCompany = new Company(0L,"2253222-2-12","Második cég Kft.","1111 Budapest, Második utca 6.",CompanyType.KFT,secondCompanyEmployees);
		companyRepository.save(secondCompany);
		
		secondCompanyEmployees.forEach(em -> {
			em.setCompany(secondCompany);
			employeeRepository.save(em);
		});
		
		List<Employee> thirdCompanyEmployees = new ArrayList<>(); 
		thirdCompanyEmployees.add(new Employee(3,"Németh Gyula","Manager",190000,LocalDateTime.of(1999, 1, 14, 10, 34)));
		thirdCompanyEmployees.add(new Employee(4,"Lakatos Zoltán","Project Manager",400000,LocalDateTime.of(2000, 1, 14, 10, 34)));
		
		Company thirdCompany = new Company(0L,"2253222-2-12","Harmadik cég Bt.","1111 Budapest, Harmadik utca 12.",CompanyType.BT,secondCompanyEmployees);
		companyRepository.save(thirdCompany);
		
		thirdCompanyEmployees.forEach(em -> {
			em.setCompany(thirdCompany);
			employeeRepository.save(em);
		});
		
		System.out.println(thirdCompany.getId());
		
		
		
		
		
	}
	

}
