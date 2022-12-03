package hu.webuni.hr.domi.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hu.webuni.hr.domi.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

	Page<Employee> findByPayGreaterThan(int pay, Pageable pageable);

	Page<Employee> findByNameLikeIgnoreCase(String string, Pageable pageable);

	Page<Employee> findByFirstWorkingDayBetween(LocalDateTime from, LocalDateTime to, Pageable page);

//	@Modifying
//    @Query(value = "truncate table employee CASCADE", nativeQuery = true)
//	void truncateEmployees();

	Page<Employee> findByPosition(String position, Pageable page);

	Optional<Employee> findByUsername(String username);
	
	

}
