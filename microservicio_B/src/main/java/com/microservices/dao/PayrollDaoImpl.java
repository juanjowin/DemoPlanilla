package com.microservices.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.RowMapper;

import com.microservices.dto.PayrollDetailDto;
import com.microservices.dto.EmployeeDto;
import com.microservices.dto.PayrollDto;
import com.microservices.util.Error;
import com.microservices.util.ExceptionNotFound;

@Component
public class PayrollDaoImpl implements PayrollDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public PayrollDto findByPayrollNumber(int PayrollNumber) {
		PayrollDto result = null;
		
		try {

			result = jdbcTemplate.queryForObject(
					"select d.*, p.* from  payroll_detail d inner join  payroll p on d.payroll_detail_id=p.payroll_number where p.payroll_number=?",
					new PayrollRowMapper(), PayrollNumber);

		} catch (Exception e) {
			throw new ExceptionNotFound("No payroll was found with number "+ PayrollNumber + " please enter a valid data" + e.getMessage());

		}
		return result;
	}

	@Override
	public EmployeeDto findByEmployeeId(int employeeId) {
		EmployeeDto result = null;

		try {
			result = jdbcTemplate.queryForObject(
					"select e.* , d.* from employee e inner join payroll_detail d on e.employee_id=d.employee_id where e.employee_id=?",
					new RowMapper<EmployeeDto>() {

						@Override
						public EmployeeDto mapRow(ResultSet rs, int rowNum) throws SQLException {

							EmployeeDto employee = new EmployeeDto();

							employee.setEmployeeId(rs.getInt(1));
							employee.setName(rs.getString(2));
							employee.setLastName(rs.getString(3));
							employee.setAdress(rs.getString(4));
							employee.setIsssNumber(rs.getString(5));
							employee.setAfp(rs.getString(6));
							employee.setAfpNumber(rs.getString(7));
							employee.setSalary(rs.getDouble(8));
							employee.setHiringDate(rs.getString(9));

							PayrollDetailDto detail = new PayrollDetailDto();
							detail.setPayrollDetailId(rs.getInt(10));
							detail.setPayrollNumber(rs.getInt(11));
							detail.setEmployeeId(rs.getInt(12));
							detail.setSalary(rs.getDouble(13));
							detail.setIsss(rs.getDouble(14));
							detail.setRenta(rs.getDouble(15));
							detail.setAfp(rs.getDouble(16));
							detail.setOtherDiscount(rs.getDouble(17));
							detail.setTotalSalaryDiscount(rs.getDouble(18));
							detail.setOtherIncome(rs.getDouble(19));
							detail.setNetIncome(rs.getDouble(20));

							employee.setDetail(detail);

							return employee;
						}

					}, employeeId);

		} catch (Exception e) {
			throw new ExceptionNotFound("No employee was found with id "+ employeeId + " please enter a valid data" + e.getMessage());
		}

		return result;

	}

	@Override
	public EmployeeDto findById(int employeeId) {
		EmployeeDto result = null;

		try {
			result = jdbcTemplate.queryForObject("select * from employee  where employee_id=?",
					new RowMapper<EmployeeDto>() {

						@Override
						public EmployeeDto mapRow(ResultSet rs, int rowNum) throws SQLException {

							EmployeeDto employee = new EmployeeDto();

							employee.setEmployeeId(rs.getInt(1));
							employee.setName(rs.getString(2));
							employee.setLastName(rs.getString(3));
							employee.setAdress(rs.getString(4));
							employee.setIsssNumber(rs.getString(5));
							employee.setAfp(rs.getString(6));
							employee.setAfpNumber(rs.getString(7));
							employee.setSalary(rs.getDouble(8));
							employee.setHiringDate(rs.getString(9));

							return employee;
						}

					}, employeeId);

		} catch (Exception e) {
			throw new ExceptionNotFound("No employee was found with id "+ employeeId + " please enter a valid data " + e.getMessage());
		}

		return result;
	}

	@Override
	public PayrollDto findByPayrollDate(Date date) {
		PayrollDto result = null;
		try {
			result = jdbcTemplate.queryForObject(
					"select d.*, p.* from  payroll_detail d inner join  payroll p on d.payroll_detail_id=p.payroll_number where p.payroll_date=?",
					new PayrollRowMapper(), date);
		} catch (Exception e) {
			throw new ExceptionNotFound("no payroll was found with date " +date+ " please enter a valid date " + e.getMessage());
		}
		return result;
	}

}
