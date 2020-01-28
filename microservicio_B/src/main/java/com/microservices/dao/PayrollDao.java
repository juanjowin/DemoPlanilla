package com.microservices.dao;

import java.sql.Date;

import com.microservices.dto.PayrollDetailDto;
import com.microservices.dto.EmployeeDto;
import com.microservices.dto.PayrollDto;

public interface PayrollDao {
	public PayrollDto findByPayrollNumber(int PayrollNumber);
	public EmployeeDto findByEmployeeId(int employeeId);
	public EmployeeDto findById(int employeeId);
	public PayrollDto findByPayrollDate(Date date);
	
}
