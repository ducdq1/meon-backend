package com.viettel.etc.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.log4j.Logger;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

/**
 * Comment
 *
 * @author hungnd
 */
@Log4j2
public class RequestUtil {
	private static final Logger LOGGER = Logger.getLogger(RequestUtil.class);

	/**
	 * CURL
	 *
	 * @param method
	 * @param requestUrl
	 * @param entity
	 * @param headerParam
	 * @param timeOutSecond miniseconds
	 * @return
	 */
	public static HttpResponse sendRequestWithTimeOut(
			HttpMethod method,
			String requestUrl,
			Object entity,
			Map<String, String> headerParam,
			int timeOutSecond) {
		try {
			RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory(timeOutSecond));
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			if (headerParam != null) {
				for (Map.Entry<String, String> entry : headerParam.entrySet()) {
					headers.add(entry.getKey(), entry.getValue());
				}
			}
			HttpEntity<Object> data = new HttpEntity<>(entity, headers);
			ResponseEntity<String> response =
					restTemplate.exchange(requestUrl, method, data, String.class);

			return new HttpResponse(response.getStatusCode(), response.getBody(), response.getHeaders());
		} catch (Exception e) {
			log.error("Request template err: " + e);
		}
		return null;
	}

	public static HttpResponse sendRequest(
			HttpMethod method, String requestUrl, Object entity, Map<String, String> headerParam) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			if (headerParam != null) {
				for (Map.Entry<String, String> entry : headerParam.entrySet()) {
					headers.add(entry.getKey(), entry.getValue());
				}
			}
			HttpEntity<Object> data = new HttpEntity<>(entity, headers);
			ResponseEntity<String> response =
					restTemplate.exchange(requestUrl, method, data, String.class);

			return new HttpResponse(response.getStatusCode(), response.getBody(), response.getHeaders());
		} catch (Exception e) {
			log.error("Request template err: " + e);
		}
		return null;
	}

	public static Object request(
			HttpMethod method, String requestUrl, Object entity, Map<String, String> headerParam) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			if (headerParam != null) {
				for (Map.Entry<String, String> entry : headerParam.entrySet()) {
					headers.add(entry.getKey(), entry.getValue());
				}
			}

			HttpEntity<Object> data = new HttpEntity<>(entity, headers);

			return restTemplate.exchange(requestUrl, method, data, Object.class).getBody();
		} catch (HttpClientErrorException e) {
			LOGGER.info(e);
			return e.getResponseBodyAsString();
		} catch (Exception e) {
			LOGGER.info(e);
			return "Error: " + e.getMessage();
		}
	}

	private static SimpleClientHttpRequestFactory getClientHttpRequestFactory(int timeOutSecond) {
		SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		// Connect timeout
		clientHttpRequestFactory.setConnectTimeout(timeOutSecond * 1000);

		// Read timeout
		clientHttpRequestFactory.setReadTimeout(timeOutSecond * 1000);
		return clientHttpRequestFactory;
	}
}

