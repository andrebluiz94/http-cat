package com.httpcat.security.Abstract;

import org.springframework.http.HttpEntity;

public abstract class AbstractGenerateHeaders {

	protected abstract HttpEntity getHeaders();
}
