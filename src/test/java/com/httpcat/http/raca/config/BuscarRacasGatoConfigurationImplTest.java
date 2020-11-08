package com.httpcat.http.raca.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
		BuscarRacasGatoConfigurationImpl.class
})
public class BuscarRacasGatoConfigurationImplTest {

	@Value("${cat.url.racas.dominio}")
	private String dominio;

	@Value("${cat.url.racas.path}")
	private String path;


	@Autowired
	private BuscarRacasGatoConfiguration configuration;

	@Test
	@DisplayName("Deve configurar a url com sucesso")
	public void deveConfigurarUrlComSucesso() {
		String url = configuration.getUrl();
		String regex = String.format("^%s%s$", dominio, path);
		boolean matches = url.matches(regex);
		assertTrue(matches);
	}
}