package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	private EntityManager entityManager;

	@Autowired
	public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Employee> findAll() {

		// create a query
		Query theQuery = entityManager.createQuery("from Employee");

		// execute query and get result list
		List<Employee> employeeList = theQuery.getResultList();

		// return the results
		return employeeList;
	}

	@Override
	public Employee findById(int theId) {

		// create a query
		Employee theEmployee = entityManager.find(Employee.class, theId);

		// return the result;
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {

		entityManager.merge(theEmployee);
	}

	@Override
	public void deleteById(int theId) {

		// retrieve the Employee with given Id
		Employee employee = entityManager.find(Employee.class, theId);

		// remove that employee
		if (employee == null) {
			System.out.println("There is no employee retrived with given id");
		} else {
			entityManager.remove(employee);
		}

	}

}
