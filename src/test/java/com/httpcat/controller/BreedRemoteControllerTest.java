package com.httpcat.controller;

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
public class BreedRemoteControllerTest {

	private static final String GATOS_API_REMOTE =  "/api/v1/remote";

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

		BDDMockito.given(buscarRacasGatoRemoteService.buscar())
				.willReturn(getCatList());

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(GATOS_API_REMOTE)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content("");

		mockMvc
				.perform(request)
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	@Test
	@DisplayName("Deve obter e salvar na base de dados todas as racas")
	public void deveBuscarESalvarNaBaseDeDados() throws Exception {

		BDDMockito.given(buscarRacasGatoRemoteService.buscar())
				.willReturn(getCatList());

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(GATOS_API_REMOTE)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content("");

		mockMvc
				.perform(request)
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	private List<ResponseRacaGato> getCatList() {
		List<ResponseRacaGato> catList = new ArrayList<>();
		catList.add(getCat());
		catList.add(getCat());
		catList.add(getCat());
		return catList;
	}

	private ResponseRacaGato getCat() {
		ResponseRacaGato cat = new ResponseRacaGato();
		cat.setTemperamento("Active");
		cat.setOrigem("Brasil");
		cat.setNome("MyCat");
		cat.setCatID("my");
		return cat;
	}
}