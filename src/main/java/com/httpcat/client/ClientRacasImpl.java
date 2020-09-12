package com.httpcat.client;

import com.httpcat.dto.RacaDto;
import com.httpcat.security.Authorization;
import com.httpcat.security.HeaderDeafault;
import com.httpcat.util.CreateUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ClientRacasImpl extends AbstractRestClient <RacaDto[]> {

	@Override
	public ResponseEntity<RacaDto[]> getAll(String itens, HttpMethod method, Class<RacaDto[]> typeReturn) {
		return super.getAll(itens, method, typeReturn);
	}

	@Override
	public ResponseEntity<RacaDto[]> getAllRacas(Class<RacaDto[]> typeReturn, HttpMethod method) {
		return super.getAllRacas(typeReturn, method);
	}
}
