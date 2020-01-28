package com.microservices.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.StampedLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.microservices.dto.PayrollDetailDto;
import com.microservices.dto.EmployeeDto;
import com.microservices.dto.PayrollDto;
import com.microservices.util.ExceptionError;


@Component
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<EmployeeDto> insertEmployee(List<EmployeeDto> employeeDto) {

		try {
			Connection conexion = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement statement = conexion
					.prepareStatement("insert into employee(name,last_name,adress,isss_number,afp,"
							+ "afp_number,salary,hiring_date) values(?,?,?,?,?,?,?,?)");

			for (EmployeeDto employee: employeeDto) {
				statement.setString(1, employee.getName());
				statement.setString(2, employee.getLastName());
				statement.setString(3, employee.getAdress());
				statement.setString(4, employee.getIsssNumber());
				statement.setString(5, employee.getAfp());
				statement.setString(6, employee.getAfpNumber());
				statement.setDouble(7, employee.getSalary());
				statement.setString(8, employee.getHiringDate());

				statement.executeUpdate();
			}

		} catch (Exception e) {
			throw new ExceptionError("problems inserting employee " + e.getMessage());
		}

		return employeeDto;
	}

}
