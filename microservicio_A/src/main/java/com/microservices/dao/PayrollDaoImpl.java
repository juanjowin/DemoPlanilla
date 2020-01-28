package com.microservices.dao;

import  java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.locks.StampedLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.SQLErrorCodes;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.microservices.dto.EmployeeDto;
import com.microservices.dto.PayrollDetailDto;
import com.microservices.dto.PayrollDto;
import com.microservices.util.ExceptionError;


@Component
public class PayrollDaoImpl implements PayrollDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<PayrollDetailDto> insertDetail(List<PayrollDetailDto> detailDto) {

		try {

			Connection conexion = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement statement = conexion
					.prepareStatement("insert into payroll_Detail(payroll_number,employee_id,salary,isss,rent,afp,"
							+ "other_discount,total_salary_discount,other_income,net_income) values(?,?,?,?,?,?,?,?,?,?)");

			for (PayrollDetailDto detail : detailDto) {
				statement.setInt(1, detail.getPayrollId());
				statement.setInt(2, detail.getEmployeeId());
				statement.setDouble(3, detail.getSalary());
				statement.setDouble(4, detail.getIsss());
				statement.setDouble(5, detail.getRenta());
				statement.setDouble(6, detail.getAfp());
				statement.setDouble(7, detail.getOtherDiscount());
				statement.setDouble(8, detail.getTotalSalaryDiscount());
				statement.setDouble(9, detail.getOtherIncome());
				statement.setDouble(10, detail.getNetIncome());

				statement.executeUpdate();

			}

		} catch (Exception e) {
			throw new ExceptionError("problems inserting payroll Detail " + e.getMessage());
		}

		return detailDto;
	}

	@Override
	public int insertPayroll(PayrollDto payrollDto) {
				
		int result = 0;
		
		try {
			result = jdbcTemplate.update(
					"insert into payroll(payroll_date,total_income,total_isss,total_rent,"
							+ "total_afp,total_payroll,state,observations) values(?,?,?,?,?,?,?,?)",
					Date.valueOf(LocalDate.now()), payrollDto.getTotalIncome(), payrollDto.getTotalIsss(),
					payrollDto.getTotalRenta(), payrollDto.getTotalAfp(), payrollDto.getTotalPayroll(),
					payrollDto.isState(), payrollDto.getObservbations());

		} catch (Exception e) {
			throw new ExceptionError("problems inserting payroll " + e.getMessage());
		}

		return result;
	}

	@Override
	public int validateIdEmployee(int id) {

		int result = 0;
		try {

			Connection conexion = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement statement = conexion
					.prepareStatement("select employee_id from employee where employee_id=?");

			statement.setInt(1, id);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			throw new ExceptionError("problems inserting payroll " + e.getMessage());
		}

		return result;
	}

}
