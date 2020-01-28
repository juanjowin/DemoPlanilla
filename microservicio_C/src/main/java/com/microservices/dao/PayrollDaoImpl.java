package com.microservices.dao;

import java.io.*;

import java.sql.Date;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.microservices.dto.PayrollDetailDto;
import com.microservices.dto.PayrollDto;
import com.microservices.util.ExceptionError;

@Component
public class PayrollDaoImpl implements PayrollDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<PayrollDetailDto> insertDetail() {

		List<PayrollDetailDto> detailList = new ArrayList<PayrollDetailDto>();

		try {

			System.out.println("read file");

			FileInputStream entrada = new FileInputStream("C:\\Users\\juana\\Documents\\Planilla.xls");

			HSSFWorkbook workbook = new HSSFWorkbook(entrada);

			Sheet primerahoja = workbook.getSheetAt(0);

			Iterator<Row> filaIterator = primerahoja.iterator();

			while (filaIterator.hasNext()) {
				Row nextRow = filaIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();

				PayrollDetailDto payrollDetail = new PayrollDetailDto();

				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();

					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 0:
						payrollDetail.setPayrollId((int) (nextCell.getNumericCellValue()));
						System.out.println(payrollDetail.getPayrollId());
						break;
					case 1:
						payrollDetail.setEmployeeId((int) (nextCell.getNumericCellValue()));
						System.out.println(payrollDetail.getEmployeeId());
						break;
					case 2:
						payrollDetail.setSalary(Double.parseDouble(String.valueOf(nextCell.getNumericCellValue())));
						System.out.println(payrollDetail.getSalary());
						break;
					case 3:
						payrollDetail
								.setOtherDiscount(Double.parseDouble(String.valueOf(nextCell.getNumericCellValue())));
						System.out.println(payrollDetail.getOtherDiscount());
						break;
					case 4:
						payrollDetail
								.setOtherIncome(Double.parseDouble(String.valueOf(nextCell.getNumericCellValue())));
						System.out.println(payrollDetail.getOtherIncome());
						break;

					}

				}

				detailList.add(payrollDetail);

			}

		} catch (Exception e) {
			throw new ExceptionError("problems inserting payroll " + e.getMessage());
		}

		return detailList;
	}

	@Override
	public int insertPayroll(PayrollDto payroll) {
		int result = 0;

		try {
			result = jdbcTemplate.update(
					"insert into payroll(payroll_date,total_income,total_isss,total_rent,"
							+ "total_afp,total_payroll,state,observations) values(?,?,?,?,?,?,?,?)",
					Date.valueOf(LocalDate.now()), payroll.getTotalIncome(), payroll.getTotalIsss(),
					payroll.getTotalRenta(), payroll.getTotalAfp(), payroll.getTotalPayroll(), payroll.isState(),
					payroll.getObservbations());

		} catch (Exception e) {
			throw new ExceptionError("problems inserting payroll " + e.getMessage());
		}

		return result;
	}

}
