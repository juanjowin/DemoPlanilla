package com.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.dto.PayrollDetailDto;
import com.microservices.service.PayrollService;

@RestController
@RequestMapping(path = "/payroll")
public class PayrollController {

	@Autowired
	private PayrollService payrollService;

	@PostMapping(value = "/insert")
	public @ResponseBody List<PayrollDetailDto> insertPayrollDetail(@RequestBody List<PayrollDetailDto> detailDto) {

		List<PayrollDetailDto> result=payrollService.insertDetail(detailDto);
		
		return  result;
	}

}
