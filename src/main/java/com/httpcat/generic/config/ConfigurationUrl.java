package com.httpcat.generic.config;

import com.httpcat.generic.expcetion.UrlBuilderException;
import org.springframework.stereotype.Component;

@Component
public abstract class ConfigurationUrl extends BuilderHeadersAuthentication implements HttpConfiguration {

	protected String url;

	@Override
	public String getUrl() {
		if(url==null) throw new UrlBuilderException("Erro ao settar a url");
		return this.url;
	}
}
