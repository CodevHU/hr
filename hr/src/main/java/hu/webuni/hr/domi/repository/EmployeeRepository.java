package hu.webuni.hr.domi.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.domi.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByPayGreaterThan(int pay);

	List<Employee> findByNameLikeIgnoreCase(String string);

	List<Employee> findByFirstWorkingDayBetween(LocalDateTime from, LocalDateTime to);

	List<Employee> findByWorkPosition(String workPosition);

	@Modifying
    @Query(value = "truncate table employee CASCADE", nativeQuery = true)
	void truncateEmployees();

}
