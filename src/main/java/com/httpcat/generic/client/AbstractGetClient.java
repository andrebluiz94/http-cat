package com.httpcat.generic.client;

import com.httpcat.generic.config.HttpConfiguration;
import com.httpcat.generic.expcetion.GetResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;

@Slf4j
public abstract class AbstractGetClient<ResponseType, Configuration extends HttpConfiguration> {


	private final RestTemplate restTemplate;

	protected Configuration configuration;
	private static Integer TYPE_RETURN_CALLER = 0;

	public AbstractGetClient(RestTemplate restTemplate, Configuration configuration) {
		this.restTemplate = restTemplate;
		this.configuration = configuration;
	}

	public ResponseEntity<ResponseType> get() {
		try{
			Class<ResponseType> typeReturn = (Class<ResponseType>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[TYPE_RETURN_CALLER];
			HttpEntity entity = new HttpEntity(configuration.buildHeadersAuthentication());
			return restTemplate.exchange(
					configuration.getUrl(),
					HttpMethod.GET,
					entity,
					typeReturn
			);
		}catch(HttpClientErrorException | HttpServerErrorException e){
			log.error("Falha ao chamar serviço, ERROR: "+ e);
			throw new GetResponseException("Falha ao chamar serviço com método Get, ERROR: "+ e.getMessage());
		}
	}
}
