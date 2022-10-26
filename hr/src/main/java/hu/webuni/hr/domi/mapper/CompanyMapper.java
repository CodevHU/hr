package hu.webuni.hr.domi.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.hr.domi.dto.CompanyDto;
import hu.webuni.hr.domi.model.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

	List<CompanyDto> companyToDtos(List<Company> all);

	CompanyDto companyToDto(Company byId);

	Company dtoToCompany(CompanyDto company);

}
