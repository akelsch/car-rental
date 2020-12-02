package de.htwsaar.prog3.carrental.repository;

import de.htwsaar.prog3.carrental.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
