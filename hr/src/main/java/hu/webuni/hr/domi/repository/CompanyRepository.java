package hu.webuni.hr.domi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.domi.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	@Modifying
    @Query(value = "truncate table company CASCADE", nativeQuery = true)
	void truncateCompanies();

	

}
