package com.microservices.services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.dao.PayrollDao;
import com.microservices.dto.PayrollDetailDto;
import com.microservices.util.ExceptionError;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class PayrollServiceImpl implements PayrollService {

	private RestTemplate resTemplate = new RestTemplate();

	@Autowired
	PayrollDao payrollDao;

	@Override
	public void insertfile() {

		try {
			
		List<PayrollDetailDto> list = null;

		List<PayrollDetailDto> detail = payrollDao.insertDetail();
		
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<List<PayrollDetailDto>> request = new HttpEntity<List<PayrollDetailDto>>(detail, header);

		ResponseEntity<List<PayrollDetailDto>> response = resTemplate.exchange("http://localhost:8092/payroll/insert",
				HttpMethod.POST, request, new ParameterizedTypeReference<List<PayrollDetailDto>>() {
				});

		list = response.getBody();
		
		} catch (Exception e) {
			throw new ExceptionError("Error .. payroll not inserted " + e.getMessage());
		}
	}

	

}
