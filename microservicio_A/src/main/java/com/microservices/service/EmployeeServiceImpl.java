package com.microservices.service;



import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.microservices.dao.EmployeeDao;

import com.microservices.dto.EmployeeDto;
import com.microservices.util.ExceptionError;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	
	@Override
	public List<EmployeeDto>  insertEmployee(List<EmployeeDto>  employeeDto) {

		// VALIDACIONES AL INSERTAR EMPLEADO
		int contador = 0;
		for (EmployeeDto employee : employeeDto) {
			contador++;
			if (employee.getName().trim()== null || employee.getName().trim().isEmpty()) {
				throw new ExceptionError("empty field name .. please insert employee name");
			} else if (employee.getSalary() <= 0) {
				throw new ExceptionError("invalid data .. insert salary greater than 0");
			} else if (employee.getHiringDate() == null || employee.getHiringDate().isEmpty()) {
				throw new ExceptionError("empty hiring date ... enter the hiring date");
			}

		}

		return employeeDao.insertEmployee(employeeDto);

	}

}
