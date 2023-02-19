package co.com.ias.closeouts.EmployeeController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ias.closeouts.exception.ResourceNotFoundException;
import co.com.ias.closeouts.model.Employee;
import co.com.ias.closeouts.repository.IEmployeeRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private IEmployeeRepository employeeRepository;

    // get all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // create employee
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // get employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        Employee employee = employeeRepository.findById(id)
                                              .orElseThrow(() -> new ResourceNotFoundException(
                                                      "Employee not exist with id :" + id));
        return ResponseEntity.ok(employee);
    }
    @GetMapping("/employees/{docNumber}")
    public ResponseEntity<Employee> getEmployeeByDocNumber(@PathVariable int employeeDocNumber){
        Employee employee = employeeRepository.findById(employeeDocNumber)
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                        "Employee not exist with document number: " + employeeDocNumber));
        return ResponseEntity.ok(employee);
    }

    // update employee
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                                              .orElseThrow(() -> new ResourceNotFoundException(
                                                      "Employee not exist with id: " + id));

        employee.setEmployeeName(employeeDetails.getEmployeeName());
        employee.setEmployeeDocNumber(employeeDetails.getEmployeeDocNumber());
        employee.setEmployeeStartDate(employeeDetails.getEmployeeStartDate());
        employee.setEmployeeSalary(employeeDetails.getEmployeeSalary());
        employee.setEmployeeProfession(employeeDetails.getEmployeeProfession());

        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

   /* @PutMapping("/employees/{docNumber}")
    public ResponseEntity<Employee> updateEmployeeByDocNumber(@PathVariable int employeeDocNumber, @RequestBody Employee employeeDetails){
        Employee employee = employeeRepository.findById(employeeDocNumber).orElseThrow(() -> new ResourceNotFoundException(
                "Employee not exist with document number: " + employeeDocNumber));
        employee.setEmployeeName(employeeDetails.getEmployeeName());
        employee.setEmployeeDocNumber(employeeDetails.getEmployeeDocNumber());
        employee.setEmployeeStartDate(employeeDetails.getEmployeeStartDate());
        employee.setEmployeeSalary(employeeDetails.getEmployeeSalary());
        employee.setEmployeeProfession(employeeDetails.getEmployeeProfession());

        Employee updateEmployeeByDocNumber = employeeRepository.save(employee);
        return ResponseEntity.ok(updateEmployeeByDocNumber);
    }*/

    // delete employee
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable int id) {
        Employee employee = employeeRepository.findById(id)
                                              .orElseThrow(() -> new ResourceNotFoundException(
                                                      "Employee not exist with id: " + id));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

  /*  @DeleteMapping("/employees/{docNumber}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployeeByDocNumber(@PathVariable int employeeDocNumber){
        Employee employee = employeeRepository.findById(employeeDocNumber).orElseThrow(() -> new ResourceNotFoundException(
                "Employee not exist with document number: " + employeeDocNumber));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }*/

}
