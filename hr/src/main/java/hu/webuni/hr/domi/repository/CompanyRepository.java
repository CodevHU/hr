package hu.webuni.hr.domi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.domi.model.Company;
import hu.webuni.hr.domi.model.EmployeeSalaryAvg;

public interface CompanyRepository extends JpaRepository<Company, Long> {

//	@Modifying
//    @Query(value = "truncate table company CASCADE", nativeQuery = true)
//	void truncateCompanies();

	List<Company> findAllByEmployeesPayGreaterThan(int salary);
	
	@Query(value = "SELECT e.position as position, AVG(e.pay) as avgSalary FROM Employee AS e LEFT JOIN Company AS c ON e.company = c.id WHERE c.id = ?1 GROUP BY e.position ORDER BY avgSalary DESC") 
	List<EmployeeSalaryAvg> getAvgByInterface(long companyId);

	@Query(value = "SELECT c FROM Company AS c FULL JOIN Employee AS e ON c.id = e.company GROUP BY c.id HAVING COUNT(*) >= ?1")
	List<Company> findCompaniesWhereTheEmployeesCountIsGreaterThanParam(Long employeeCount);

	@EntityGraph("Company.full")
	@Query("SELECT c FROM Company c")
	List<Company> findAllWithEmployees();

	@EntityGraph("Company.full")
	@Query("SELECT c FROM Company c WHERE c.id = :id")
	Company findByIdWithEmployee(long id);

	

	

}
