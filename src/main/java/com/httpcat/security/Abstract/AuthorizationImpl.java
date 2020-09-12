package com.httpcat.security.Abstract;

import com.httpcat.security.Authorization;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationImpl extends AbstractAuthorization implements Authorization {

	private String authorizationKey;

	public AuthorizationImpl(String authorizationKey) {
		this.authorizationKey = authorizationKey;
	}

	public AuthorizationImpl() {
	}

	@Override
	public HttpHeaders getAuthrization(HttpHeaders httpHeaders) {
		httpHeaders.add("x-api-key", authorizationKey.trim());
		return httpHeaders;
	}

	@Override
	public HttpHeaders setAuthrization(String key, HttpHeaders httpHeaders) {
		setAuthorizationKey(key);
		httpHeaders.add("x-api-key", getAuthorizationKey());
		return httpHeaders;
	}

	public String getAuthorizationKey() {
		return authorizationKey;
	}
	public void setAuthorizationKey(String authorizationKey) {
		this.authorizationKey = authorizationKey.trim();
	}
}
