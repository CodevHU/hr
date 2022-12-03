package hu.webuni.hr.domi.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.webuni.hr.domi.dto.LeaveDto;
import hu.webuni.hr.domi.model.Leave;

@Mapper(componentModel = "spring")
public interface LeaveMapper {

	@IterableMapping(qualifiedByName = "summary")
	List<LeaveDto> leavesToDtos(List<Leave> content);
	
	@Mapping(target = "employee.company.employees", ignore = true)
	@Mapping(target = "employee.position.employees", ignore = true)
	@Mapping(target = "approver.company.employees", ignore = true)
	@Mapping(target = "approver.position.employees", ignore = true)
	@Named("summary")
	LeaveDto leaveToSummaryDto(Leave leave);

	Leave dtoToLeave(LeaveDto leave);

	LeaveDto leaveToDto(Leave create);

}
