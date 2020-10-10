package com.httpcat.generic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public abstract class BuilderHeadersAuthentication implements HttpConfiguration{

	@Value("${cat.authorization.key}")
	private String KEY_AUTHORIZATION;

	@Override
	public HttpHeaders buildHeadersAuthentication() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, KEY_AUTHORIZATION);
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}
}
