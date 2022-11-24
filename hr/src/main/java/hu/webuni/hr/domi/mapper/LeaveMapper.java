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
	
	@Mapping(target = "createdBy.company.employees", ignore = true)
	@Mapping(target = "createdBy.position.employees", ignore = true)
	@Mapping(target = "superior.company.employees", ignore = true)
	@Mapping(target = "superior.position.employees", ignore = true)
	@Named("summary")
	LeaveDto leaveToSummaryDto(Leave leave);

	
	
}
