package com.microservices.service;

import java.sql.Date;

import com.microservices.dto.EmployeeDto;
import com.microservices.dto.PayrollDto;

public interface PayrollService {
	public PayrollDto findByPayrollNumber(int PayrollNumber);
	public EmployeeDto findByEmployeeId(int employeeId);
	public EmployeeDto findById(int employeeId);
	public PayrollDto findByPayrollDate(Date date);
}
