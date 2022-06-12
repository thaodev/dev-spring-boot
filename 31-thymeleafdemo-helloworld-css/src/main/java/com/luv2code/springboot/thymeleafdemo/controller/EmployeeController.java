package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private List<Employee> employeeList = null;
	
	@PostConstruct
	private void loadData() {
	Employee employee1 = new Employee(1, "Thao", "Nguyen", "thaontt1408@gmail.com");
	Employee employee2 = new Employee(2, "Mike", "Vo", "mikevo@gmail.com");
	Employee employee3 = new Employee(3, "Lan", "Huong", "lanhuong@gmail.com");
	Employee employee4 = new Employee(4, "Rachel", "Nguy", "rachelnguy@gmail.com");
	
	employeeList = new ArrayList<>();
	employeeList.add(employee1);
	employeeList.add(employee2);
	employeeList.add(employee3);
	employeeList.add(employee4);
	
	}	
	
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		
		//add to the spring model
		theModel.addAttribute("employees", employeeList);
		
		return "employee-list";
	}
}
