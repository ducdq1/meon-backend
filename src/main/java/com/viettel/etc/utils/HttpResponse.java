package com.viettel.etc.utils;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

/**
 * Comment
 *
 * @author hungnd
 */
@Data
public class HttpResponse {
	public HttpStatus status;
	public String body;
	public HttpHeaders headers;

	public HttpResponse() {

	}

	public HttpResponse(HttpStatus status, String body, HttpHeaders headers) {
		this.status = status;
		this.body = body;
		this.headers = headers;
	}
}
