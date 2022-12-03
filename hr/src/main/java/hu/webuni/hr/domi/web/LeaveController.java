package hu.webuni.hr.domi.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.domi.dto.LeaveDto;
import hu.webuni.hr.domi.dto.PageDto;
import hu.webuni.hr.domi.mapper.LeaveMapper;
import hu.webuni.hr.domi.model.Leave;
import hu.webuni.hr.domi.model.LeaveSearchCriteria;
import hu.webuni.hr.domi.service.LeaveService;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {
	
	@Autowired
	private LeaveService leaveService;
	
	@Autowired
	private LeaveMapper leaveMapper;

	@GetMapping
	public PageDto getAll(Pageable pageable) {
		
		Page<Leave> leaves = leaveService.getAll(pageable);
		PageDto leavePage = new PageDto(leaves.getNumber(), leaves.getTotalElements(), leaves.getTotalPages(), leaveMapper.leavesToDtos(leaves.getContent()));
		
		return leavePage;
	}
	
	@GetMapping("/filter")
	public PageDto filter(Pageable pageable, @RequestBody LeaveSearchCriteria example) {
		
		Page<Leave> leaves = leaveService.find(pageable, example);
		
		PageDto leavePage = new PageDto(leaves.getNumber(), leaves.getTotalElements(), leaves.getTotalPages(), leaveMapper.leavesToDtos(leaves.getContent()));
		
		return leavePage;
	}
	
	@PostMapping("/feedback/{id}")
	public LeaveDto feedback(@PathVariable long id, @RequestBody @Valid LeaveDto leave) {
		return leaveMapper.leaveToSummaryDto(leaveService.updateStatus(id,leaveMapper.dtoToLeave(leave)));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("#leave.createdBy.id == authentication.principal.employee.id")
	public LeaveDto update(@PathVariable long id, @RequestBody @Valid LeaveDto leave) {
		System.out.println(leave.getEmployee().getId());
		return leaveMapper.leaveToSummaryDto(leaveService.update(id, leaveMapper.dtoToLeave(leave)));
		
	}
	
	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable long id) {
		
		leaveService.delete(id);
		
		return true;
		
	}
	
}
