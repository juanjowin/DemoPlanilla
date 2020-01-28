package com.microservices.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class PayrollDto {

	private int payrollNumber;
	private Date payrollDate;
	private double totalIncome;
	private double totalIsss;
	private double totalRenta;
	private double totalAfp;
	private double TotalPayroll;
	private boolean state;
	private String observbations;

	List<PayrollDetailDto> payrollDetail = new ArrayList<PayrollDetailDto>();

	public PayrollDto() {

	}

	public PayrollDto(Date payrollDate, double totalIncome, double totalIsss, double totalRenta, double totalAfp,
			double totalPayroll, boolean state, String observbations, List<PayrollDetailDto> payrollDetail) {
		super();
		this.payrollDate = payrollDate;
		this.totalIncome = totalIncome;
		this.totalIsss = totalIsss;
		this.totalRenta = totalRenta;
		this.totalAfp = totalAfp;
		TotalPayroll = totalPayroll;
		this.state = state;
		this.observbations = observbations;
		this.payrollDetail = payrollDetail;
	}

	public int getPayrollNumber() {
		return payrollNumber;
	}

	public Date getPayrollDate() {
		return payrollDate;
	}

	public double getTotalIncome() {
		return totalIncome;
	}

	public double getTotalIsss() {
		return totalIsss;
	}

	public double getTotalRenta() {
		return totalRenta;
	}

	public double getTotalAfp() {
		return totalAfp;
	}

	public double getTotalPayroll() {
		return TotalPayroll;
	}

	public boolean isState() {
		return state;
	}

	public String getObservbations() {
		return observbations;
	}

	public List<PayrollDetailDto> getPayrollDetail() {
		return payrollDetail;
	}

	public void setPayrollNumber(int payrollNumber) {
		this.payrollNumber = payrollNumber;
	}

	public void setPayrollDate(Date payrollDate) {
		this.payrollDate = payrollDate;
	}

	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}

	public void setTotalIsss(double totalIsss) {
		this.totalIsss = totalIsss;
	}

	public void setTotalRenta(double totalRenta) {
		this.totalRenta = totalRenta;
	}

	public void setTotalAfp(double totalAfp) {
		this.totalAfp = totalAfp;
	}

	public void setTotalPayroll(double totalPayroll) {
		TotalPayroll = totalPayroll;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public void setObservbations(String observbations) {
		this.observbations = observbations;
	}

	public void setPayrollDetail(List<PayrollDetailDto> payrollDetail) {
		this.payrollDetail = payrollDetail;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PayrollDto [payrollNumber=").append(payrollNumber).append(", payrollDate=").append(payrollDate)
				.append(", totalIncome=").append(totalIncome).append(", totalIsss=").append(totalIsss)
				.append(", totalRenta=").append(totalRenta).append(", totalAfp=").append(totalAfp)
				.append(", TotalPayroll=").append(TotalPayroll).append(", state=").append(state)
				.append(", observbations=").append(observbations).append(", payrollDetail=").append(payrollDetail)
				.append("]");
		return builder.toString();
	}


}
