package com.microservices.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.microservices.dto.PayrollDetailDto;
import com.microservices.dto.PayrollDto;

public class PayrollRowMapper implements RowMapper<PayrollDto> {

	@Override
	public PayrollDto mapRow(ResultSet rs, int rowNum) throws SQLException {

		PayrollDto payroll = new PayrollDto();

		payroll.setPayrollNumber(rs.getInt("payroll_number"));
		payroll.setPayrollDate(rs.getDate("payroll_date"));
		payroll.setTotalIncome(rs.getDouble("total_income"));
		payroll.setTotalIsss(rs.getDouble("total_isss"));
		payroll.setTotalRenta(rs.getDouble("total_rent"));
		payroll.setTotalAfp(rs.getDouble("total_afp"));
		payroll.setTotalPayroll(rs.getDouble("total_payroll"));
		payroll.setState(rs.getBoolean("state"));
		payroll.setObservbations(rs.getString("observations"));

		List<PayrollDetailDto> payrollList = new ArrayList<PayrollDetailDto>();

		while (rs.next()) {
			PayrollDetailDto payrollDatail = new PayrollDetailDto();

			payrollDatail.setPayrollDetailId(rs.getInt("payroll_detail_id"));
			payrollDatail.setPayrollNumber(rs.getInt("payroll_number"));
			payrollDatail.setEmployeeId(rs.getInt("employee_id"));
			payrollDatail.setSalary(rs.getDouble("salary"));
			payrollDatail.setIsss(rs.getDouble("isss"));
			payrollDatail.setRenta(rs.getDouble("rent"));
			payrollDatail.setAfp(rs.getDouble("afp"));
			payrollDatail.setOtherDiscount(rs.getDouble("other_discount"));
			payrollDatail.setTotalSalaryDiscount(rs.getDouble("total_salary_discount"));
			payrollDatail.setOtherIncome(rs.getDouble("other_income"));
			payrollDatail.setNetIncome(rs.getDouble("net_income"));

			payrollList.add(payrollDatail);
			payroll.setPayrollDetail(payrollList);
		}

		return payroll;
	}

}
