package com.httpcat.generic.config;

import org.springframework.http.HttpEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface HttpConfiguration {
	HttpEntity buildHeadersAuthentication();
	String getUrl();
}
