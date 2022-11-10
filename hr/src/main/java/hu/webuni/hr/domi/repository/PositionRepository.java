package hu.webuni.hr.domi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.domi.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {

	List<Position> findByPositionName(String positionName);

}
