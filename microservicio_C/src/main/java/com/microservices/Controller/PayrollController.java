package com.microservices.Controller;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microservices.services.PayrollService;



@RestController
@RequestMapping(path = "/read")
public class PayrollController {

	@Autowired
	private PayrollService payrollService;

	@PostMapping(value = "/file")
	
	public void insertfile() {

		payrollService.insertfile();

	}
	
	
	 

}
