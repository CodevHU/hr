package hu.webuni.hr.domi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.domi.model.Employee;
import hu.webuni.hr.domi.model.Leave;
import hu.webuni.hr.domi.model.Leave.Status;
import hu.webuni.hr.domi.model.LeaveSearchCriteria;
import hu.webuni.hr.domi.repository.CompanyRepository;
import hu.webuni.hr.domi.repository.EmployeeRepository;
import hu.webuni.hr.domi.repository.LeaveRepository;

@SpringBootTest
@AutoConfigureTestDatabase
public class LeaveServiceTestIT {
	
	@Autowired
	LeaveService leaveService;
	
	@Autowired
	LeaveRepository leaveRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	 
	private Employee createdBy;
	private Employee superior;
	
	@BeforeEach
	public void init() {
		leaveRepository.deleteAll();
		employeeRepository.deleteAll();
		companyRepository.deleteAll();
		
		this.createdBy = employeeRepository.save(new Employee(0L, "Kiss Elemér", null, 0, LocalDateTime.now().minusDays(2)));
		this.superior = employeeRepository.save(new Employee(0L, "Felettes István", null, 0, LocalDateTime.now().minusDays(5)));
	}
	
	@Test
	void testCreateLeave() throws Exception {
		
		Leave leave = leaveRepository.save(new Leave(0L, LocalDate.now().plusDays(2), LocalDate.now().plusDays(6), createdBy));
		
		assertThat(leaveRepository.findById(leave.getId()).get().getId()).isEqualTo(leave.getId());
		assertThat(leaveRepository.findById(leave.getId()).get().getCreatedBy().getId()).isEqualTo(leave.getCreatedBy().getId());
		assertThat(leaveRepository.findById(leave.getId()).get().getStatus()).isEqualTo(Status.PENDING);
		assertThat(leaveRepository.findById(leave.getId()).get().getStartDate()).isEqualTo(leave.getStartDate());
		assertThat(leaveRepository.findById(leave.getId()).get().getEndDate()).isEqualTo(leave.getEndDate());
		
	}
	
	@Test
	void testIfDeleteLeaveWhenStatusIsNotPendingThenThrowException() throws Exception {
		
		Leave leave = leaveRepository.save(new Leave(0L, LocalDate.now().plusDays(2), LocalDate.now().plusDays(6), createdBy,superior,Status.ACCAPTED,null));
		
		assertThrows(ResponseStatusException.class,() -> leaveService.delete(leave.getId()));
		
	}
	
	@Test
	void testIfUpdateLeaveWhenStatusIsNotPendingThenThrowException() throws Exception {
		
		Leave leave = leaveRepository.save(new Leave(0L, LocalDate.now().plusDays(2), LocalDate.now().plusDays(6), createdBy,superior,Status.ACCAPTED,null));
		
		assertThrows(ResponseStatusException.class,() -> leaveService.update(leave.getId(),
				new Leave(0L, LocalDate.now().plusDays(2), LocalDate.now().plusDays(6), createdBy,superior,Status.ACCAPTED,null)
				));
		
	}
	
	@Test
	void testThatFindLeaveByEmployeeName() throws Exception {
		Leave leave = leaveRepository.save(new Leave(0L, LocalDate.now().plusDays(2), LocalDate.now().plusDays(6), createdBy));
		
		LeaveSearchCriteria example = new LeaveSearchCriteria();
		example.setCreatedBy(createdBy);
		
		Pageable paging = PageRequest.of(0, 10000);
		
		List<Leave> findList = leaveService.find(paging, example).getContent();
		
		assertThat(findList)
		.usingRecursiveFieldByFieldElementComparator()
		.contains(leaveRepository.findById(leave.getId()).get());
	}
	
	@Test
	void testThatFindLeaveByEmployeeNameAndStatus() throws Exception {
		Leave leave = leaveRepository.save(new Leave(0L, LocalDate.now().plusDays(2), LocalDate.now().plusDays(6), createdBy));
		
		LeaveSearchCriteria example = new LeaveSearchCriteria();
		example.setCreatedBy(createdBy);
		example.setStatus(Status.PENDING);
		
		Pageable paging = PageRequest.of(0, 10000);
		
		List<Leave> findList = leaveService.find(paging, example).getContent();
		
		assertThat(findList)
		.usingRecursiveFieldByFieldElementComparator()
		.contains(leaveRepository.findById(leave.getId()).get());
	}
	
	@Test
	void testThatNotFindLeaveByLeaveDate() throws Exception {
		Leave leave = leaveRepository.save(new Leave(0L, LocalDate.now().plusDays(2), LocalDate.now().plusDays(6), createdBy));
		
		LeaveSearchCriteria example = new LeaveSearchCriteria();
		example.setLeaveDateFrom(LocalDate.now().plusDays(1));
		example.setLeaveDateTo(LocalDate.now().plusDays(1));
		
		Pageable paging = PageRequest.of(0, 10000);
		
		List<Leave> findList = leaveService.find(paging, example).getContent();
		
		assertThat(findList)
		.usingRecursiveFieldByFieldElementComparator()
		.doesNotContain(leaveRepository.findById(leave.getId()).get());
	}
	
	@Test
	void testThatFindLeaveByCreatedAtDate() throws Exception {
		Leave leave = leaveRepository.save(new Leave(0L, LocalDate.now().plusDays(2), LocalDate.now().plusDays(6), createdBy));
		
		LeaveSearchCriteria example = new LeaveSearchCriteria();
		example.setCreatedAtFrom(LocalDate.now());
		example.setCreatedAtTo(LocalDate.now());
		
		Pageable paging = PageRequest.of(0, 10000);
		
		List<Leave> findList = leaveService.find(paging, example).getContent();
		
		assertThat(findList)
		.usingRecursiveFieldByFieldElementComparator()
		.contains(leaveRepository.findById(leave.getId()).get());
	}

}
