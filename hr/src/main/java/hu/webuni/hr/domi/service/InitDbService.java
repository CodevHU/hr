package hu.webuni.hr.domi.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.domi.model.Company;
import hu.webuni.hr.domi.model.CompanyType;
//import hu.webuni.hr.domi.model.Company.CompanyType;
import hu.webuni.hr.domi.model.Employee;
import hu.webuni.hr.domi.model.Position;
import hu.webuni.hr.domi.model.Position.Qualification;
import hu.webuni.hr.domi.repository.CompanyRepository;
import hu.webuni.hr.domi.repository.CompanyTypeRepository;
import hu.webuni.hr.domi.repository.EmployeeRepository;
import hu.webuni.hr.domi.repository.PositionRepository;

@Service
public class InitDbService{
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PositionRepository positionRepository;
	
	@Autowired
	CompanyTypeRepository companyTypeRepository;

	@Transactional
	public void clearDB() {
		employeeRepository.truncateEmployees();		
		companyRepository.truncateCompanies();		
	}
	
	@Transactional
	public void insertTestData() {
				
		CompanyType newCompanyType = new CompanyType();
		newCompanyType.setName("KFT");
		CompanyType companyTypeKft = companyTypeRepository.save(newCompanyType);
		
		CompanyType newCompanyType2 = new CompanyType();
		newCompanyType2.setName("BT");
		CompanyType companyTypeBt = companyTypeRepository.save(newCompanyType2);
		
		Position manager = positionRepository.save(new Position(0L,"Manager", Qualification.GRADUATION, 180000));
		Position ceo = positionRepository.save(new Position(0L,"CEO", Qualification.UNIVERSITY, 400000));
		Position coordinator = positionRepository.save(new Position(0L,"Coordinator", Qualification.GRADUATION, 200000));
		Position projectManager = positionRepository.save(new Position(0L,"Project Manager", Qualification.GRADUATION, 250000));
		
		Employee newEmployee1 = employeeRepository.save(new Employee(1,"Kóst Elek",ceo,1000000, LocalDateTime.of(2010, 1, 14, 10, 34)));
		newEmployee1.setPosition(projectManager);
		
		Employee newEmployee2 = employeeRepository.save(new Employee(2,"Lapos Elemér",coordinator,200000,LocalDateTime.of(2014, 1, 14, 10, 34)));
		newEmployee2.setPosition(ceo);
		
		Employee newEmployee3 = employeeRepository.save(new Employee(3,"Lusta Gyula",manager,100000,LocalDateTime.of(2018, 1, 14, 10, 34)));
		newEmployee3.setPosition(coordinator);
		
		Employee newEmployee4 = employeeRepository.save(new Employee(3,"Juhász István",manager,300000,LocalDateTime.of(2018, 1, 14, 10, 34)));
		newEmployee4.setPosition(manager);
		
		Employee newEmployee5 = employeeRepository.save(new Employee(4,"Kiss Zoltán",projectManager,800000,LocalDateTime.of(2020, 1, 14, 10, 34)));
		newEmployee5.setPosition(manager);
		
		Company firstCompany = new Company(0L,"3233222-2-5","Első cég Kft.","1111 Budapest, Első utca 3.",null);
		firstCompany.setCompanyType(companyTypeKft);
		Company newCompany = companyRepository.save(firstCompany);
		
		newCompany.addEmployee(newEmployee1);
		newCompany.addEmployee(newEmployee2);
		newCompany.addEmployee(newEmployee3);
		newCompany.addEmployee(newEmployee4);
		newCompany.addEmployee(newEmployee5);
		
		
		
		Employee newEmployee6 = employeeRepository.save(new Employee(1,"Kovács Elek",ceo,1100000,LocalDateTime.of(2020, 1, 14, 10, 34)));
		newEmployee6.setPosition(ceo);
		
		Employee newEmployee7 = employeeRepository.save(new Employee(2,"Varga Elemér",coordinator,60000,LocalDateTime.of(2010, 1, 14, 10, 34)));
		newEmployee7.setPosition(coordinator);
		
		Employee newEmployee8 = employeeRepository.save(new Employee(3,"Fekete Gyula",manager,80000,LocalDateTime.of(2016, 1, 14, 10, 34)));
		newEmployee8.setPosition(manager);
		
		Company secondCompany = new Company(0L,"2253222-2-12","Második cég Bt.","1111 Budapest, Második utca 6.",null);
		secondCompany.setCompanyType(companyTypeBt);
		Company newSecondCompany = companyRepository.save(secondCompany);
		
		newSecondCompany.addEmployee(newEmployee6);
		newSecondCompany.addEmployee(newEmployee7);
		newSecondCompany.addEmployee(newEmployee8);
		
	}
	

}
