package com.httpcat.generic.expcetion;

import org.springframework.web.client.HttpServerErrorException;

public class PostServerResquestException extends RuntimeException {
	public PostServerResquestException(String MSG, HttpServerErrorException error) {
		super(MSG, error);
	}
}
