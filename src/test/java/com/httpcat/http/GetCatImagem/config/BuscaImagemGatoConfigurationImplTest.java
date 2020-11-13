package com.httpcat.http.GetCatImagem.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
		BuscaImagemGatoConfigurationImpl.class
})
@ActiveProfiles("test")
public class BuscaImagemGatoConfigurationImplTest {


	@Value("${cat.url.racas.dominio}")
	private String dominio;

	@Value("${cat.url.racas.path.imagem}")
	private String path;

	@Autowired
	private BuscaImagemGatoConfiguration configuration;

	@Test
	public void deveConfigurarUrlComSucesso() {

		Map<String, String> expand  = new HashMap<>();
		expand.put("breed_id", "");
		expand.put("category_ids", "");

		configuration.setUrlParams(expand);
		String url = configuration.getUrl();

		String urlExpect = getUrlExpect(expand);

		assertEquals(urlExpect, url);

	}

	private String getUrlExpect(Map<String, String> expand) {
		return UriComponentsBuilder
				.fromHttpUrl(dominio)
				.path(path)
				.buildAndExpand(expand)
				.toUriString();
	}
}