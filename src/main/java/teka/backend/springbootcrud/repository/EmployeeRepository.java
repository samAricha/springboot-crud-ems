package teka.backend.springbootcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teka.backend.springbootcrud.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


}
