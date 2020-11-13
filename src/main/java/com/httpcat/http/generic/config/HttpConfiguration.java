package com.httpcat.http.generic.config;

import org.springframework.http.HttpHeaders;

public interface HttpConfiguration {
	HttpHeaders buildHeadersAuthentication();

	String getUrl();
}
