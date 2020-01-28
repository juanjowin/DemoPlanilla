package com.microservices.dao;

import java.util.List;

import com.microservices.dto.EmployeeDto;
import com.microservices.dto.PayrollDetailDto;
import com.microservices.dto.PayrollDto;

public interface PayrollDao {

	public List<PayrollDetailDto> insertDetail( List<PayrollDetailDto> detailDto);
	public int insertPayroll(PayrollDto payrollDto);
	public int validateIdEmployee(int id);

}
