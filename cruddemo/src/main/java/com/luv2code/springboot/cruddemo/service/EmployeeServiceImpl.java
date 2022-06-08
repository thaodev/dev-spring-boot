package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDAO employeeDAO;

	@Override
	public List<Employee> findAll() {
		
		return employeeDAO.findAll();
	}

	@Override
	public Employee findById(int theId) {
		
		return employeeDAO.findById(theId);
	}

	@Override
	public void save(Employee theEmployee) {
		employeeDAO.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		employeeDAO.deleteById(theId);
	}

}
