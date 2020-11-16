package com.httpcat.http.generic.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(properties = {
		"cat.url.racas.dominio=",
		"cat.url.racas.path="
})
@ActiveProfiles("test")
class ConfigurationUrlTest  extends ConfigurationUrl{


	private final String token = "fake-token";
	@Test
	@DisplayName("Deve retonar Erro quando url estiver vazia")
	void returnErrorQuandoUrlEstiverVazia() {
		this.url = null;
		NullPointerException urlBuilderException = assertThrows(NullPointerException.class, this::getUrl);
		Assertions.assertThat(urlBuilderException).isInstanceOf(NullPointerException.class);
	}

	@Test
	@DisplayName("Deve retonar o header com key authorization")
	void deveRetonrarHeaderComAuthorization() {
		HttpHeaders httpHeaders = this.buildHeadersAuthentication();
		System.out.println(httpHeaders.get(HttpHeaders.AUTHORIZATION));
		assertEquals(httpHeaders.get(HttpHeaders.AUTHORIZATION).get(0), token);
		assertEquals(httpHeaders.get(HttpHeaders.CONTENT_TYPE).get(0), MediaType.APPLICATION_JSON_VALUE);
	}
}