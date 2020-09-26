package com.httpcat.generic.config;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

public interface HttpConfiguration {
	HttpHeaders buildHeadersAuthentication();
	String getUrl();
}
