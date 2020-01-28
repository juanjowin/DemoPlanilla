package com.microservices.dao;

import java.util.List;

import com.microservices.dto.PayrollDetailDto;
import com.microservices.dto.EmployeeDto;
import com.microservices.dto.PayrollDto;

public interface EmployeeDao {

	public List<EmployeeDto> insertEmployee(List<EmployeeDto> employeeDto);
}
