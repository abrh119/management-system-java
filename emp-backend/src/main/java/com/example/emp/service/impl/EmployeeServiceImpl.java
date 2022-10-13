package com.example.emp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.emp.exception.ResourceNotFoundExpception;
import com.example.emp.model.Employee;
import com.example.emp.repository.EmployeeRepository;
import com.example.emp.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}


	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
		return employeeRepository.save(employee);
	}


	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}


	@Override
	public Employee getEmployeeById(long id) {
		// TODO Auto-generated method stub
		/*Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			return employee.get();
		}
		else {
			throw new ResourceNotFoundExpception("Employee", "id", id);
		} */
		
		// Lamda Expression
		
		return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExpception("Empployee", "Id", id));
		
	}


	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// TODO Auto-generated method stub
		// Checks if emp exists or not
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExpception("Employee", "id", id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		// Save EMp to DB
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}


	@Override
	public void deleteEmployee(long id) {
		
		// If emp exist to delete or not
		employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExpception("Employee", "Id", id));
		
		employeeRepository.deleteById(id);
		// TODO Auto-generated method stub
		
	}

}
