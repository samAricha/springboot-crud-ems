package teka.backend.springbootcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teka.backend.springbootcrud.exception.ResourceNotFoundException;
import teka.backend.springbootcrud.model.Employee;
import teka.backend.springbootcrud.repository.EmployeeRepository;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/employees")
//public class EmployeeController implements CommandLineRunner {
public class EmployeeController{
    @Autowired
    public EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }


    //create Employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    //get Employee by id
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with the stated Id doesn't exist"));

        return ResponseEntity.ok(employee);
    }

    //update Employee
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        employeeRepository.save(employee);

        return ResponseEntity.ok(employee);
    }

    //delete Employee
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employeeRepository.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }










    //CODE FOR SAVING EMPLOYEE OBJECTS TO DB WHEN CLASS IS INSTANTIATED

//    @Override
//    public void run(String... args) throws Exception {
//        Employee employee = new Employee();
//        employee.setFirstName("George");
//        employee.setLastName("Magoha");
//        employee.setEmailId("george@gmail.com");
//        employeeRepository.save(employee);
//
//        Employee employee1 = new Employee();
//        employee1.setFirstName("Rufus");
//        employee1.setLastName("Munene");
//        employee1.setEmailId("rufus@gmail.com");
//        employeeRepository.save(employee1);
//    }
}
