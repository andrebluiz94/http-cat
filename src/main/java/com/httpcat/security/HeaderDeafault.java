package com.httpcat.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;

public interface HeaderDeafault {

	HttpEntity getHeaders();
	HttpEntity getHeaders(String key);
}
