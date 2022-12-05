package hu.webuni.hr.domi.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.domi.model.Company;
import hu.webuni.hr.domi.model.CompanyType;
//import hu.webuni.hr.domi.model.Company.CompanyType;
import hu.webuni.hr.domi.model.Employee;
import hu.webuni.hr.domi.model.Leave;
import hu.webuni.hr.domi.model.Leave.Status;
import hu.webuni.hr.domi.model.Position;
import hu.webuni.hr.domi.model.Position.Qualification;
import hu.webuni.hr.domi.repository.CompanyRepository;
import hu.webuni.hr.domi.repository.CompanyTypeRepository;
import hu.webuni.hr.domi.repository.EmployeeRepository;
import hu.webuni.hr.domi.repository.LeaveRepository;
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
	
	@Autowired
	LeaveRepository leaveRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Transactional
	public void clearDB() {
//		employeeRepository.truncateEmployees();		
//		companyRepository.truncateCompanies();		
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
		
		Employee newEmployee1 = employeeRepository.save(new Employee(0L,"Kóst Elek",ceo,1000000, LocalDateTime.of(2010, 1, 14, 10, 34)));
		newEmployee1.setPosition(projectManager);
		newEmployee1.setUsername("user1");
		newEmployee1.setPassword(passwordEncoder.encode("pass"));
		
		Employee newEmployee2 = employeeRepository.save(new Employee(0L,"Lapos Elemér",coordinator,200000,LocalDateTime.of(2014, 1, 14, 10, 34)));
		newEmployee2.setPosition(ceo);
		newEmployee2.setUsername("user2");
		newEmployee2.setPassword(passwordEncoder.encode("pass"));
		newEmployee2.setSuperior(newEmployee1);
		
		Employee newEmployee3 = employeeRepository.save(new Employee(0L,"Lusta Gyula",manager,100000,LocalDateTime.of(2018, 1, 14, 10, 34)));
		newEmployee3.setPosition(coordinator);
		newEmployee3.setUsername("user3");
		newEmployee3.setPassword(passwordEncoder.encode("pass"));
		
		Employee newEmployee4 = employeeRepository.save(new Employee(0L,"Juhász István",manager,300000,LocalDateTime.of(2018, 1, 14, 10, 34)));
		newEmployee4.setPosition(manager);
		newEmployee4.setUsername("user4");
		newEmployee4.setPassword(passwordEncoder.encode("pass"));
		newEmployee4.setSuperior(newEmployee2);
		
		Employee newEmployee5 = employeeRepository.save(new Employee(0L,"Kiss Zoltán",projectManager,76000,LocalDateTime.of(2020, 1, 14, 10, 34)));
		newEmployee5.setPosition(manager);
		newEmployee5.setUsername("user5");
		newEmployee5.setPassword(passwordEncoder.encode("pass"));
		newEmployee5.setSuperior(newEmployee2);
		
		Company firstCompany = new Company(0L,"3233222-2-5","Első cég Kft.","1111 Budapest, Első utca 3.",null);
		firstCompany.setCompanyType(companyTypeKft);
		Company newCompany = companyRepository.save(firstCompany);
		
		newCompany.addEmployee(newEmployee1);
		newCompany.addEmployee(newEmployee2);
		newCompany.addEmployee(newEmployee3);
		newCompany.addEmployee(newEmployee4);
		newCompany.addEmployee(newEmployee5);
		
		
		
		Employee newEmployee6 = employeeRepository.save(new Employee(0L,"Kovács Elek",ceo,1100000,LocalDateTime.of(2020, 1, 14, 10, 34)));
		newEmployee6.setPosition(ceo);
		newEmployee6.setUsername("user6");
		newEmployee6.setPassword(passwordEncoder.encode("pass"));
		
		Employee newEmployee7 = employeeRepository.save(new Employee(0L,"Varga Elemér",coordinator,60000,LocalDateTime.of(2010, 1, 14, 10, 34)));
		newEmployee7.setPosition(coordinator);
		newEmployee7.setUsername("user7");
		newEmployee7.setPassword(passwordEncoder.encode("pass"));
		newEmployee7.setSuperior(newEmployee2);
		
		Employee newEmployee8 = employeeRepository.save(new Employee(0L,"Fekete Gyula",manager,80000,LocalDateTime.of(2016, 1, 14, 10, 34)));
		newEmployee8.setPosition(manager);
		newEmployee8.setUsername("user8");
		newEmployee8.setPassword(passwordEncoder.encode("pass"));
		
		Company secondCompany = new Company(0L,"2253222-2-12","Második cég Bt.","1111 Budapest, Második utca 6.",null);
		secondCompany.setCompanyType(companyTypeBt);
		Company newSecondCompany = companyRepository.save(secondCompany);
		
		newSecondCompany.addEmployee(newEmployee6);
		newSecondCompany.addEmployee(newEmployee7);
		newSecondCompany.addEmployee(newEmployee8);
		
		
		
		Leave leave1 = new Leave(0L, LocalDate.now().plusDays(2),LocalDate.now().plusDays(4),newEmployee7);
		Leave leave2 = new Leave(0L, LocalDate.now().plusDays(3),LocalDate.now().plusDays(10),newEmployee8);
		Leave leave3 = new Leave(0L, LocalDate.now().plusDays(10),LocalDate.now().plusDays(15),newEmployee1,Status.ACCAPTED);
		leave3.setApprover(newEmployee2);
		
		Leave leave4 = new Leave(0L, LocalDate.now().plusDays(13),LocalDate.now().plusDays(20),newEmployee2,Status.DENIDED);
		leave4.setApprover(newEmployee6);
		
		leaveRepository.save(leave1);
		leaveRepository.save(leave2);
		leaveRepository.save(leave3);
		leaveRepository.save(leave4);
		
		
		
	}
	

}
