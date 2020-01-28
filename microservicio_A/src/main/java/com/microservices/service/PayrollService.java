package com.microservices.service;

import java.util.List;

import com.microservices.dto.PayrollDetailDto;


public interface PayrollService {
	public List<PayrollDetailDto> insertDetail(List<PayrollDetailDto> detailDto);



}
