package com.viettel.etc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigValue {

	@Value("${ws.patients.url}")
	private String patientsSearchUrl;

	public String getPatientsSearchUrl() {
		return patientsSearchUrl;
	}

	public void setPatientsSearchUrl(String patientsSearchUrl) {
		this.patientsSearchUrl = patientsSearchUrl;
	}
}
