package com.httpcat.security.Abstract;

import org.springframework.http.HttpHeaders;

public abstract class AbstractAuthorization {

	protected abstract HttpHeaders getAuthrization(HttpHeaders httpHeaders);
}
