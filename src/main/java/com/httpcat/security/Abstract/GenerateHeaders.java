package com.httpcat.security.Abstract;

import com.httpcat.security.HeaderDeafault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class GenerateHeaders implements HeaderDeafault {

	AuthorizationImpl authorization = new AuthorizationImpl();
	HttpHeaders httpHeaders = new HttpHeaders();

	@Override
	public HttpEntity getHeaders() {
		authorization.getAuthrization(httpHeaders);
		return new HttpEntity(httpHeaders);
	}

	@Override
	public HttpEntity getHeaders(String key) {
		authorization.setAuthrization(key, httpHeaders);
		return new HttpEntity(httpHeaders);
	}

}
