package com.luv2code.springboot.cruddemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.entity.Salary;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	@GetMapping("/list")
	public String findAllEmployees(Model theModel){
		List<Employee> theEmployees = employeeService.findAllEmployees();
		
		theModel.addAttribute("employees", theEmployees);
		
		return "employees/list-employees";
	}
	
	//this is only for API
//	@GetMapping("list/{theId}")
//	public Employee findEmployeeById(@PathVariable int theId) {
//		return employeeService.findEmployeeById(theId);
//	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//create model attribute to bind form data
		Employee theEmployee = new Employee();
		
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
	}
	
	@GetMapping("/showSalaryDepartment")
	public String showSalaryDepartment(Model theModel) {
		
		//create model attribute to bind form data
		Salary theSalary = new Salary();
		
		theModel.addAttribute("salary", theSalary);
		
		return "employees/salary-structure-form";
	}
	
	@GetMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		//save Employee
		employeeService.saveEmployee(theEmployee);
		
		return "redirect:/employees/list";
	}
	
}
