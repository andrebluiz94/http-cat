package com.httpcat.generic.client;

import com.httpcat.generic.config.HttpConfiguration;
import com.httpcat.generic.expcetion.PostClientRequestException;
import com.httpcat.generic.expcetion.PostServerResquestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;

@Slf4j
public abstract class AbstractPostClient<RequestType, ResponseType, Configuration extends HttpConfiguration> {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private Configuration configuration;
	private static Integer TYPE_RETURN_CALLER = 1;


	public ResponseEntity<ResponseType> postRequest(RequestType requestType) {
		Class<ResponseType> typeReturn = (Class<ResponseType>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[TYPE_RETURN_CALLER];
		HttpEntity entity = new HttpEntity(requestType, configuration.buildHeadersAuthentication());
		try {
			ResponseEntity<ResponseType> response = restTemplate.exchange(
					configuration.getUrl(),
					HttpMethod.POST,
					entity,
					typeReturn
			);
			log.info("Response da chamada ao servidor: ", response);
			return response;
		} catch (HttpClientErrorException e) {
			log.error("Erro ao enviar a mensagem",e);
			throw new PostClientRequestException("Erro no cliente ao fazer a requisição", e);
		}catch (HttpServerErrorException e){
			log.error("Erro no servidor ao fazer a mensagem", e);
			throw new PostServerResquestException("Erro no servidor no seridor", e);
		}
	}
}
