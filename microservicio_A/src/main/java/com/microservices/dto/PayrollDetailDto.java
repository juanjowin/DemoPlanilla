package com.microservices.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.microservices.util.Error;

public class PayrollDetailDto {

	private int payrollId;
	private int employeeId;
	private double salary;
	private double isss;
	private double renta;
	private double afp;
	private double otherDiscount;
	private double totalSalaryDiscount;
	private double otherIncome;
	private double netIncome;

	private List<Error> errores;

	@JsonIgnore
	EmployeeDto employee = new EmployeeDto();

	public PayrollDetailDto() {

	}

	public PayrollDetailDto(int employeeId, double salary, double isss, double renta, double afp, double otherDiscount,
			double totalSalaryDiscount, double otherIncome, double netIncome) {
		super();

		this.employeeId = employeeId;
		this.salary = salary;
		this.isss = isss;
		this.renta = renta;
		this.afp = afp;
		this.otherDiscount = otherDiscount;
		this.totalSalaryDiscount = totalSalaryDiscount;
		this.otherIncome = otherIncome;
		this.netIncome = netIncome;

	}

	public int getPayrollId() {
		return payrollId;
	}

	public void setPayrollId(int payrollId) {
		this.payrollId = payrollId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public double getSalary() {
		return salary;
	}

	public double getIsss() {
		return isss;
	}

	public double getRenta() {
		return renta;
	}

	public double getAfp() {
		return afp;
	}

	public double getOtherDiscount() {
		return otherDiscount;
	}

	public double getTotalSalaryDiscount() {
		return totalSalaryDiscount;
	}

	public double getOtherIncome() {
		return otherIncome;
	}

	public double getNetIncome() {
		return netIncome;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setIsss(double isss) {
		this.isss = isss;
	}

	public void setRenta(double renta) {
		this.renta = renta;
	}

	public void setAfp(double afp) {
		this.afp = afp;
	}

	public void setOtherDiscount(double otherDiscount) {
		this.otherDiscount = otherDiscount;
	}

	public void setTotalSalaryDiscount(double totalSalaryDiscount) {
		this.totalSalaryDiscount = totalSalaryDiscount;
	}

	public void setOtherIncome(double otherIncome) {
		this.otherIncome = otherIncome;
	}

	public void setNetIncome(double netIncome) {
		this.netIncome = netIncome;
	}

	public List<Error> getErrores() {
		return errores;
	}

	public void setErrores(List<Error> errores) {
		this.errores = errores;
	}

	public EmployeeDto getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDto employee) {
		this.employee = employee;
	}

}
