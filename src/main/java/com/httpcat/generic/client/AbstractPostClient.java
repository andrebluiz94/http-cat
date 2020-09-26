package com.httpcat.generic.client;

import com.httpcat.generic.config.HttpConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractPostClient<RequestType, ResponseType, Configuration extends HttpConfiguration> {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private Configuration configuration;
	private static Integer TYPE_RETURN_CALLER = 1;

	public ResponseEntity<ResponseType> postRequest(RequestType requestType) {
		Class<ResponseType> typeReturn = (Class<ResponseType>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[TYPE_RETURN_CALLER];
		HttpEntity entity = new HttpEntity(requestType, configuration.buildHeadersAuthentication().getHeaders());
		return restTemplate.exchange(
				configuration.getUrl(),
				HttpMethod.POST,
				entity,
				typeReturn
		);
	}
}
