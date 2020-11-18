package com.httpcat.controller;

import com.httpcat.Entity.Cat;
import com.httpcat.http.GetCatImagem.service.BuscaImagemGatoService;
import com.httpcat.http.raca.service.BuscarGatosLocalService;
import com.httpcat.http.raca.service.BuscarRacasGatoRemoteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BreedLocalControllerImplTest {

	private static final String GATOS_API =  "/api/v1/";

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

		BDDMockito.given(service.getCats())
				.willReturn(getCatList());

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(GATOS_API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content("");

		mockMvc
				.perform(request)
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	@Test
	@DisplayName("Deve obter uma raça de gato pesquisada pelo nome")
	public void devePesquisarRacaPeloNome() throws Exception {

		BDDMockito.given(service.getCatByName(Mockito.anyString()))
				.willReturn(getCatOptional());

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(GATOS_API+"/raca")
				.param("nome", "myMake")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content("");

		mockMvc
				.perform(request)
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	@Test
	@DisplayName("Deve obter raças de gato pesquisadas pelo temperamento")
	public void devePesquisarRacasCatPeloTemperamento() throws Exception {

		BDDMockito.given(service.getCatByTemperamento(Mockito.anyString()))
				.willReturn(getCatList());

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(GATOS_API+"/temperamento")
				.param("temperamento", "active")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content("");

		mockMvc
				.perform(request)
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	@Test
	@DisplayName("Deve obter raças pesquisados pelo nome")
	public void devePesquisarUmaListaDeGatosPeloNome() throws Exception {

		BDDMockito.given(service.getCatByTemperamento(Mockito.anyString()))
				.willReturn(getCatList());

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(GATOS_API + "nomes")
				.param("lista", "gato1", "gato2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content("");

		mockMvc
				.perform(request)
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}


	private ResponseEntity<Optional<Cat>> getCatOptional() {
		return ResponseEntity.ok(Optional.of(getCat()));
	}

	private ResponseEntity<List<Cat>> getCatList() {
		List<Cat> catList = new ArrayList<>();
		catList.add(getCat());
		return ResponseEntity.ok(catList);
	}

	private Cat getCat() {
		Cat cat = new Cat();
		cat.setTemperament("Active");
		cat.setOrigin("Brasil");
		cat.setName("MyCat");
		cat.setId("my");
		return cat;
	}
}