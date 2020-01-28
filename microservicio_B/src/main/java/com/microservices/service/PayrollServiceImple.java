package com.microservices.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.dao.PayrollDao;
import com.microservices.dto.EmployeeDto;
import com.microservices.dto.PayrollDto;
import com.microservices.util.Error;
import com.microservices.util.ExceptionNotFound;

@Service
public class PayrollServiceImple implements PayrollService {

	@Autowired
	private PayrollDao payrollDao;

	@Override
	public PayrollDto findByPayrollNumber(int number) {
		
		Error error= new Error();

	
		PayrollDto payroll =payrollDao.findByPayrollNumber(number);
		if (payroll == null) {
			//throw new ExceptionNotFound(
			//		"No form was found "+ number + " please insert valid data");
		} else if (payroll.getPayrollNumber() < 0) {
			throw new ExceptionNotFound(
					"invalid data ... no form number less than 0 is accepted .. please enter a valid data");
		}

		return payroll;
	}

	@Override
	public EmployeeDto findByEmployeeId(int employeeId) {

		EmployeeDto employee = payrollDao.findByEmployeeId(employeeId);
		if (employee == null) {
			throw new ExceptionNotFound("could not find employee with id " + employeeId);
		
		}
		return employee;
	}
	
	@Override
	public EmployeeDto findById(int employeeId) {
		EmployeeDto employee= payrollDao.findById(employeeId);
		if(employee==null) {
			throw new ExceptionNotFound("could not find employee with id " + employeeId);
		}
		return employee;
	}

	@Override
	public PayrollDto findByPayrollDate(Date date) {

		PayrollDto payroll = payrollDao.findByPayrollDate(date);
		if (payroll == null) {
			throw new ExceptionNotFound(
					"No se encontro planilla con fecha " + date + " porfavor inserte fecha valida");
		}

		return payroll;
	}


}
