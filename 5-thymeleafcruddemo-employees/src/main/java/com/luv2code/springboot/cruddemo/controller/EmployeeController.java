package com.luv2code.springboot.cruddemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springboot.cruddemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
}
