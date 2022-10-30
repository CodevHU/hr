package hu.webuni.hr.domi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.domi.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	

}
