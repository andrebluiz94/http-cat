package com.httpcat.generic.config;

import org.springframework.util.Assert;

public abstract class ConfigurationUrl extends BuilderHeaders implements HttpConfiguration {

	protected String url;

	@Override
	public String getUrl() {
		Assert.notNull(url, "Url não pode ser NULLA");
		return this.url;
	}
}
