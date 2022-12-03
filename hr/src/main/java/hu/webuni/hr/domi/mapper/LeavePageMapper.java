package hu.webuni.hr.domi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.hr.domi.dto.PageDto;
import hu.webuni.hr.domi.model.LeavePage;

@Mapper(componentModel = "spring")
public interface LeavePageMapper {

	@Mapping(target = "leaves.employee", ignore = true)
	@Mapping(target = "leaves.approver", ignore = true)
	@Mapping(target = "leaves.status", ignore = true)
	@Mapping(target = "leaves.company", ignore = true)
	@Mapping(target = "leaves.employees", ignore = true)
	@Mapping(target = "leaves.companies", ignore = true)
	PageDto leavePageToDto(LeavePage leavePage);

	

}
