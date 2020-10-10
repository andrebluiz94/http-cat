package com.httpcat.generic.client;

import com.httpcat.generic.config.HttpConfiguration;
import com.httpcat.generic.expcetion.GetResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;

@Slf4j
public abstract class AbstractGetClient<RequestType, ResponseType, Configuration extends HttpConfiguration> {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	protected Configuration configuration;
	private static Integer TYPE_RETURN_CALLER = 1;

	public ResponseType get() {
		try{
			Class<ResponseType> typeReturn = (Class<ResponseType>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[TYPE_RETURN_CALLER];
			HttpEntity entity = new HttpEntity(configuration.buildHeadersAuthentication());
			return restTemplate.exchange(
					configuration.getUrl(),
					HttpMethod.GET,
					entity,
					typeReturn
			).getBody();
		}catch(HttpStatusCodeException e){
			log.error("FAlha ao chamar, ERROR: "+ e);
			throw new GetResponseException("Falha na chamada Get, ERROR: "+ e);
		}
	}
}
