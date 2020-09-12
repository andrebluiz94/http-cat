package com.httpcat.security;

import org.springframework.http.HttpHeaders;

public interface Authorization {
	HttpHeaders getAuthrization(HttpHeaders httpHeaders);
	HttpHeaders setAuthrization(String key, HttpHeaders httpHeaders);
}
