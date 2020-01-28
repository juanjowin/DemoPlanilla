package com.microservices.dto;

import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeDto {

	private int employeeId;
	private String name;
	private String lastName;
	private String adress;
	private String isssNumber;
	private String afp;
	private String afpNumber;
	private double salary;
	private String hiringDate;
	
	@Autowired
	PayrollDetailDto detail;

	public EmployeeDto() {

	}

	public EmployeeDto(String name, String lastName, String adress, String isssNumber, String afp, String afpNumber,
			double salary, String hiringDate, PayrollDetailDto detail) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.adress = adress;
		this.isssNumber = isssNumber;
		this.afp = afp;
		this.afpNumber = afpNumber;
		this.salary = salary;
		this.hiringDate = hiringDate;
		this.detail = detail;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAdress() {
		return adress;
	}

	public String getIsssNumber() {
		return isssNumber;
	}

	public String getAfp() {
		return afp;
	}

	public String getAfpNumber() {
		return afpNumber;
	}

	public double getSalary() {
		return salary;
	}

	public String getHiringDate() {
		return hiringDate;
	}

	public PayrollDetailDto getDetail() {
		return detail;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public void setIsssNumber(String isssNumber) {
		this.isssNumber = isssNumber;
	}

	public void setAfp(String afp) {
		this.afp = afp;
	}

	public void setAfpNumber(String afpNumber) {
		this.afpNumber = afpNumber;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setHiringDate(String hiringDate) {
		this.hiringDate = hiringDate;
	}

	public void setDetail(PayrollDetailDto detail) {
		this.detail = detail;
	}

	

}
