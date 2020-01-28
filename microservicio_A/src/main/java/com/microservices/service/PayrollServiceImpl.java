package com.microservices.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.config.Configuration;
import com.microservices.dao.PayrollDao;
import com.microservices.dto.PayrollDetailDto;
import com.microservices.dto.PayrollDto;
import com.microservices.util.Error;
import com.microservices.util.ExceptionError;

@Service
public class PayrollServiceImpl implements PayrollService {

	@Autowired
	private PayrollDao payrollDao;
	
	@Autowired
	private Configuration configuration;
	

	@Override
	public List<PayrollDetailDto> insertDetail(List<PayrollDetailDto> detailDto) {

		List<PayrollDetailDto> payroll = fillPayroll(detailDto);

		List<PayrollDetailDto> detailPayroll = payrollDao.insertDetail(payroll);

		if (detailPayroll == null) {
			throw new ExceptionError("Error inserting payroll");
		}

		return detailPayroll;
	}

	// *********************** METODO PARA LLENAR PLANILLA  *********************
	private List<PayrollDetailDto> fillPayroll(List<PayrollDetailDto> detail) {
		
		double totalIngreso = 0.0;
		double totalISSS = 0.0;
		double totalRenta = 0.0;
		double totalAfp = 0.0;
		double totalPlanilla = 0.0;

		List<PayrollDetailDto> deteailList = new ArrayList<PayrollDetailDto>();

	
		try {
					
		for (PayrollDetailDto payrollDetail : detail) {

			// ************************ PIDIENDO DATOS DE PLANILLA ***********************************
			
			int resultEmployeeId = payrollDao.validateIdEmployee(payrollDetail.getEmployeeId());
			if (resultEmployeeId > 0) {
				
				PayrollDetailDto detailDto = new PayrollDetailDto();

				detailDto.setPayrollId(payrollDetail.getPayrollId());
				detailDto.setEmployeeId(resultEmployeeId);
				detailDto.setSalary(payrollDetail.getSalary());
				detailDto.setOtherDiscount(payrollDetail.getOtherDiscount());
				detailDto.setOtherIncome(payrollDetail.getOtherIncome());
				
				if (payrollDetail.getPayrollId() <= 0 || payrollDetail.getPayrollId()>1) {
					throw new ExceptionError("invalid data .. insert valid payroll id number");		
				} 
				else if (payrollDetail.getEmployeeId() <= 0) {
					throw new ExceptionError("invalid data .. insert valid employee id number");
				} 
				else if (payrollDetail.getSalary() <= 0) {
					throw new ExceptionError("invalid data .. insert salary greater than 0");
				} 
				else if (payrollDetail.getOtherDiscount() < 0) {
					throw new ExceptionError("invalid data .. insert discount greater than 0");
				}
				else if (payrollDetail.getOtherIncome() < 0) {
					throw new ExceptionError("invalid data .. insert income greater than 0 ");
				}

				// ******* CALCULOS PARA LLENAR DETALLE DE PLANILLA **********
				
				double sueldoBase = payrollDetail.getSalary();

				// calculo de retncion ISSS
				double retencionIsss = 0.0;
				if (sueldoBase < 1000) {
					retencionIsss = sueldoBase * configuration.getIsssMenor();
				} else {
					retencionIsss = configuration.getIsssMayor();
				}

				//calculo de retencion renta
				double retencionRenta = 0.0;
				if (sueldoBase < 487.60) {
					retencionRenta = 0.0;
				} else if (sueldoBase >= 487.61 && sueldoBase < 642.85) {
					retencionRenta = (sueldoBase - 487.60) * 0.10 + 17.48;
				} else if (sueldoBase >= 642.86 && sueldoBase < 915.81) {
					retencionRenta = (sueldoBase - 642.85) * 0.10 + 32.70;
				} else if (sueldoBase >= 915.82 && sueldoBase < 2058.67) {
					retencionRenta = (sueldoBase - 915.81) * 0.20 + 60;
				} else if (sueldoBase >= 2058.68) {
					retencionRenta = (sueldoBase - 2058.67) * 0.30 + 288.57;
				}

				// calculo retencion AFP
				double retencionAfp = 0.0;		
					retencionAfp = sueldoBase * configuration.getAfp();
					

				//*****************LLENANDO DETALLE DE PLANILLA ****************************
				
				detailDto.setIsss(retencionIsss);
				detailDto.setRenta(retencionRenta);
				detailDto.setAfp(retencionAfp);

				// calculo total sueldo menos descuentos
				double sueldoDescuentos = 0.0;
				sueldoDescuentos = sueldoBase - retencionIsss - retencionAfp - retencionRenta
						- payrollDetail.getOtherDiscount();

				detailDto.setTotalSalaryDiscount(sueldoDescuentos);

				// calculo sueldo neto
				double sueldoNetoAPagar = 0.0;
				sueldoNetoAPagar = sueldoDescuentos + payrollDetail.getOtherIncome();

				detailDto.setNetIncome(sueldoNetoAPagar);

				deteailList.add(detailDto);

				// **************** CALCULOS PARA LLENAR PLANILLA ***********************
				totalIngreso = totalIngreso + sueldoBase + payrollDetail.getOtherIncome();
				totalISSS = totalISSS + retencionIsss;
				totalRenta = totalRenta + retencionRenta;
				totalAfp = totalAfp + retencionAfp;
				totalPlanilla = totalIngreso - totalISSS - totalRenta - totalAfp;

			} else {
				throw new ExceptionError("Employee with id " + payrollDetail.getEmployeeId()
						+ " does not exist... please enter a valid id");
			}
		}
		
		
		// LLENAR Y GUARDAR PLANILLA *******************************
		
	
		PayrollDto payroll = new PayrollDto();

		payroll.setTotalIncome(totalIngreso);
		payroll.setTotalIsss(totalISSS);
		payroll.setTotalRenta(totalRenta);
		payroll.setTotalAfp(totalAfp);
		payroll.setTotalPayroll(totalPlanilla);
		payroll.setState(true);
		payroll.setObservbations(payroll.getObservbations());

		payrollDao.insertPayroll(payroll);
		
		} catch (Exception ex) {
			throw new ExceptionError("Error adding payroll " + ex.getMessage());
		}
	
		return deteailList;

	}
}
