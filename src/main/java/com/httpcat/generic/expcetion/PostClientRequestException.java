package com.httpcat.generic.expcetion;

import org.springframework.web.client.HttpClientErrorException;

public class PostClientRequestException extends RuntimeException {
	public PostClientRequestException(String MSG, HttpClientErrorException e) {
		super(MSG, e);
	}
}
