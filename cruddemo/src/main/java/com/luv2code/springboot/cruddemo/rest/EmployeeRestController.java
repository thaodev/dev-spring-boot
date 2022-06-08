package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeService employeeService;
	
	//quick and dirty: inject employee service
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	//expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll() {
		
		return employeeService.findAll();
	}
	
	//expose "/employees/id" to return the given employee
	@GetMapping("/employees/{theId}")
	public Employee getEmployee(@PathVariable int theId) {
		
		Employee theEmployee = employeeService.findById(theId);
		
		if(theEmployee == null) {
			throw new RuntimeException("Employee is not found - " + theId);
		}
		
		return theEmployee;
	}
	
	//expose "POST/employees" to save or update employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		
		theEmployee.setId(0);
		
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	//expose "PUT/employees" to update existing employee
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	//expose "DELETE/employee/{theId} to deleve the employee
	@DeleteMapping("/employees/{theId}")
	public String deleteEmployee(@PathVariable int theId) {
		
		Employee theDeleteEmployee = employeeService.findById(theId);
		
		if (theDeleteEmployee == null) {
			throw new RuntimeException("Employee is not found - " + theId);
		}
		
		return "The employee with id - " + theId + " was deleted";
	}
	
}
