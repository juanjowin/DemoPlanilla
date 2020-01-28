package com.microservices.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.microservices.dto.PayrollDetailDto;

import com.microservices.dto.PayrollDto;

public interface PayrollDao {
	
	public List<PayrollDetailDto> insertDetail();
	public int insertPayroll(PayrollDto payroll);

}
