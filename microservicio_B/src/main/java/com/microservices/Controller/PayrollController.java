package com.microservices.Controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.dto.EmployeeDto;
import com.microservices.dto.PayrollDto;
import com.microservices.service.PayrollService;
import com.microservices.util.ExceptionNotFound;

import antlr.debug.NewLineEvent;

@RestController
@RequestMapping(path = "/payroll")
public class PayrollController {

	@Autowired
	private PayrollService payrollService;

	@GetMapping(value = "/number/{number}")
	public @ResponseBody PayrollDto findByPayrollNumber(@PathVariable("number") int number) {

		PayrollDto result = payrollService.findByPayrollNumber(number);
		
		return result;

	}

	@GetMapping(value = "/employee/{id}")
	public @ResponseBody EmployeeDto findByEmployeeId(@PathVariable("id") int employeeId) {

		return payrollService.findByEmployeeId(employeeId);
	}

	@GetMapping(value = "/date/{date}")
	public @ResponseBody PayrollDto findByPayrollDate(@PathVariable("date") Date date) {

		return payrollService.findByPayrollDate(date);
	}

	@GetMapping(value = "only/employee/{id}")
	public @ResponseBody EmployeeDto findById(@PathVariable("id") int employeeId) {

		return payrollService.findById(employeeId);

	}
}
