package com.microservices.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("microservicio-a")
public class Configuration {

	private double afp;
	private double isssMayor;
	private double isssMenor;
	
	public double getIsssMenor() {
		return isssMenor;
	}
	public void setIsssMenor(double isssMenor) {
		this.isssMenor = isssMenor;
	}
	public double getAfp() {
		return afp;
	}
	public double getIsssMayor() {
		return isssMayor;
	}
	public void setAfp(double afp) {
		this.afp = afp;
	}
	public void setIsssMayor(double isssMayor) {
		this.isssMayor = isssMayor;
	}


	
	
}
