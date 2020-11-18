package com.httpcat.controller;

import com.httpcat.http.GetCatImagem.dto.ResponseRacaGatoImagem;
import com.httpcat.http.GetCatImagem.service.BuscaImagemGatoService;
import com.httpcat.http.raca.dto.ResponseRacaGato;
import com.httpcat.http.raca.service.BuscarGatosLocalService;
import com.httpcat.http.raca.service.BuscarRacasGatoRemoteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class ImagemRacaGatoControllerImplTest {


	private static final String GATOS_API_IMAGEM =  "/api/v2/imagem";

	@Autowired
	MockMvc mockMvc;

	@MockBean
	BuscarGatosLocalService service;

	@MockBean
	BuscarRacasGatoRemoteService buscarRacasGatoRemoteService;

	@MockBean
	BuscaImagemGatoService buscaImagemGatoService;

	@Test
	@DisplayName("Deve obter todas as racas de gato")
	public void deveObterTodasAsRacas() throws Exception {

		BDDMockito.given(buscaImagemGatoService.buscar())
				.willReturn(getImageCat());

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(GATOS_API_IMAGEM)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content("");

		mockMvc
				.perform(request)
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	@Test
	@DisplayName("Deve obter todas as racas de gato")
	public void deveImagemCatTemperamentoCategoria() throws Exception {

		BDDMockito.given(buscaImagemGatoService.buscar())
				.willReturn(getImageCat());

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(GATOS_API_IMAGEM + "/search")
				.param("raca", "cat")
				.param("categoria", "1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content("");

		mockMvc
				.perform(request)
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	private List<ResponseRacaGatoImagem> getImageCat() {
		ResponseRacaGatoImagem imagem = new ResponseRacaGatoImagem();
		imagem.setUrl("Http://cat");
		imagem.setIdRaca("mycat");
		imagem.setRaca(getResponseCat());
		imagem.setUrl("Http://cat");
		return null;
	}

	private List<ResponseRacaGato> getResponseCat() {
		List<ResponseRacaGato> cat = new ArrayList<>();

		cat.add(getCatMy());
		return cat;
	}

	private ResponseRacaGato getCatMy() {
		ResponseRacaGato cat = new ResponseRacaGato();
		cat.setCatID("mycat");
		cat.setOrigem("Obrasil");
		cat.setTemperamento("Active");
		cat.setNome("My");
		return cat;
	}
}