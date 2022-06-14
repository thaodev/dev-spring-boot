package com.luv2code.springboot.cruddemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		//save Employee
		employeeService.saveEmployee(theEmployee);
		
		return "redirect:/employees/list";
	}
	
	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel){
		//retrieve the employee by given id per parameter from service
		Employee theEmployee = employeeService.findEmployeeById(theId);

		//set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employee", theEmployee);

		//send over to our form
		return "employees/employee-form";

	}
//this method use the @PathVariable instead of @RequestParam annotation but you can't use @PathVariable
// in this scenario as user sent a param from the employee list form
//	@GetMapping("/showFormForUpdate/{theId}")
//	public String showFormForUpdate(@PathVariable int theId, Model theModel){
//		//retrieve the employee by given id per parameter from service
//		Employee theEmployee = employeeService.findEmployeeById(theId);
//
//		//set employee as a model attribute to pre-populate the form
//		theModel.addAttribute("employee", theEmployee);
//
//		//send over to our form
//		return "employees/employee-form";
//	}

	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam("employeeId") int theId){
		employeeService.deleteById(theId);
		return "redirect:/employees/list";
	}
	
}
