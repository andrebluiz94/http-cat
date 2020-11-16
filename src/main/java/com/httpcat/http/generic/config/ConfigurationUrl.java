package com.httpcat.http.generic.config;

import org.springframework.stereotype.Component;

@Component
public abstract class ConfigurationUrl extends BuilderHeadersAuthentication implements HttpConfiguration {

	protected String url;

	@Override
	public String getUrl() {
		if (url.isEmpty()){
			throw new NullPointerException("Erro: url vazia");
		}
		return this.url;
	}
}
