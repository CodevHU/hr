package hu.webuni.hr.domi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.domi.model.Employee;
import hu.webuni.hr.domi.model.Leave;
import hu.webuni.hr.domi.model.Leave.Status;
import hu.webuni.hr.domi.model.LeaveSearchCriteria;
import hu.webuni.hr.domi.repository.EmployeeRepository;
import hu.webuni.hr.domi.repository.LeaveRepository;

@Service
public class LeaveService {
	
	@Autowired
	LeaveRepository leaveRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	
	public Page<Leave> getAll(Pageable page) {
		Page<Leave> leaves = leaveRepository.findAll(page);
		
		return leaves;
	}
	
	
	@Transactional
	public Leave updateStatus(long id, Leave updatedLeave) {
		
		System.out.println(updatedLeave.getSuperior().getId());
		
		Leave leave = leaveRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		
		
		Employee superior = employeeRepository.findById(updatedLeave.getSuperior().getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		leave.setStatus(updatedLeave.getStatus());
		leave.setSuperior(superior);
		
		return leaveRepository.save(leave);
		
	}
	

	@Transactional
	public void delete(long id) {
		
		Leave leave = leaveRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		if(leave.getStatus() != Status.PENDING) throw new ResponseStatusException(HttpStatus.NOT_FOUND); 
			
		leaveRepository.delete(leave);
		
	}
	
	@Transactional
	public Leave update(long id, Leave leave) {
		
		Leave origLeave = leaveRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		if(origLeave.getStatus() != Status.PENDING) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		leave.setId(id);
		leave.setStatus(Status.PENDING);
		leave.setCreatedAt(origLeave.getCreatedAt());
		leave.setCreatedBy(origLeave.getCreatedBy());
		
		return leaveRepository.save(leave);
	}
	
	public Page<Leave> find(Pageable page, LeaveSearchCriteria example){
		
		Specification<Leave> spec = Specification.where(null);
		
		if(example.getStatus() != null) {
			spec = spec.and(LeaveSpecifications.hasStatus(example.getStatus()));
		}
		
		if(example.getCreatedBy() != null && StringUtils.hasText(example.getCreatedBy().getName())) {
			spec = spec.and(LeaveSpecifications.hasCreatedEmployeeName(example.getCreatedBy().getName()));
		}
		
		if(example.getSuperior() != null && StringUtils.hasText(example.getSuperior().getName())) {
			spec = spec.and(LeaveSpecifications.hasSuperiorEmployeeName(example.getSuperior().getName()));
		}
		
		if(example.getCreatedAtFrom() != null && example.getCreatedAtTo() != null) {
			spec = spec.and(LeaveSpecifications.hasCreatedAt(example.getCreatedAtFrom(),example.getCreatedAtTo()));
		}
		
		if(example.getLeaveDateFrom() != null && example.getLeaveDateTo() != null) {
			spec = spec.and(LeaveSpecifications.hasLeaveDate(example.getLeaveDateFrom(),example.getLeaveDateTo()));
		}
		
		return leaveRepository.findAll(spec, page);
	}

	
}
