package com.httpcat.http.raca.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.httpcat.Entity.Cat;
import com.httpcat.http.raca.config.BuscarRacasGatoConfiguration;
import com.httpcat.http.raca.dto.ResponseRacaGato;
import com.httpcat.http.raca.mapper.MapperResponseRacaGatoParaEntityCat;
import com.httpcat.http.raca.service.BuscarRacasGatoRemoteService;
import com.httpcat.repository.RacasRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.RequestDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockserver.model.HttpRequest.request;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes ={
		RestTemplate.class,
		ObjectMapper.class,
		BuscarRacasGatoRemoteServiceImpl.class,
		MapperResponseRacaGatoParaEntityCat.class
})
public class BuscarRacasGatoRemoteServiceImplTest {

	@Autowired
	private BuscarRacasGatoRemoteService service;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private BuscarRacasGatoConfiguration buscarRacasGatoConfiguration;

	@MockBean
	private RacasRepository repository;



	private ClientAndServer clientAndServer;
	private MockServerClient mockServer;

	private static final String path = "/breeds";
	private static final String AUTHORIZATION = "4303135a-adc5-4e29-9524-0293f39fccb4";
	private static final String LOCALHOST = "localhost";
	private static final int PORT = 9090;

	@BeforeEach
	void setUp() {
		clientAndServer = ClientAndServer.startClientAndServer(PORT);
		mockServer = new MockServerClient(LOCALHOST, PORT);
	}

	@Test
	@DisplayName("Deve buscar gatos e retornar com sucesso")
	public void deveBuscarGatosERetornarComSucesso() throws JsonProcessingException {

		String catList = objectMapper.writeValueAsString(getContentCat());

		BDDMockito.when(buscarRacasGatoConfiguration.getUrl())
				.thenReturn(getUrl());

		BDDMockito.when(buscarRacasGatoConfiguration.buildHeadersAuthentication())
				.thenReturn(getheaders());

		mockServer.when(getResquestExpect())
				.respond(HttpResponse.response()
						.withStatusCode(HttpStatus.OK.value())
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.withBody(catList));

		ResponseRacaGato buscarResultado = service.buscar().get(0);
		ResponseRacaGato contentCat = getContentCat().get(0);
		assertEquals(contentCat.getCatID(), buscarResultado.getCatID());
		assertEquals(contentCat.getNome(), buscarResultado.getNome());
		assertEquals(contentCat.getOrigem(), buscarResultado.getOrigem());
		assertEquals(contentCat.getTemperamento(), buscarResultado.getTemperamento());
	}

	@Test
	@DisplayName("Deve retornar error se a api cats estiver com problema")
	public void deveRetornarErrorAoBuscarNaAPICats() {

		BDDMockito.when(buscarRacasGatoConfiguration.getUrl())
				.thenReturn(getUrl());

		BDDMockito.when(buscarRacasGatoConfiguration.buildHeadersAuthentication())
				.thenReturn(getheaders());

		mockServer.when(getResquestExpect())
				.respond(HttpResponse.response()
						.withStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

		ResponseStatusException internalError = assertThrows(ResponseStatusException.class, () -> service.buscar());
		assertThat(internalError).isInstanceOf(ResponseStatusException.class);

	}


	@Test
	@DisplayName("Deve buscar e salvar a lista de gatos no banco de dados")
	public void deveBuscarESalvarGatosNaBaseDeDados() throws JsonProcessingException {
		String catList = objectMapper.writeValueAsString(getContentCat());
		List<Cat> cats = new ArrayList<>();
		BDDMockito.when(buscarRacasGatoConfiguration.getUrl())
				.thenReturn(getUrl());

		BDDMockito.when(buscarRacasGatoConfiguration.buildHeadersAuthentication())
				.thenReturn(getheaders());

		BDDMockito.when(repository.saveAll(Mockito.any()))
				.thenReturn(cats);

		mockServer.when(getResquestExpect())
				.respond(HttpResponse.response()
						.withStatusCode(HttpStatus.OK.value())
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.withBody(catList));

		ResponseRacaGato buscarResultado = service.buscar().get(0);
		ResponseRacaGato contentCat = getContentCat().get(0);
		assertEquals(contentCat.getCatID(), buscarResultado.getCatID());
		assertEquals(contentCat.getNome(), buscarResultado.getNome());
		assertEquals(contentCat.getOrigem(), buscarResultado.getOrigem());
		assertEquals(contentCat.getTemperamento(), buscarResultado.getTemperamento());
	}

	@Test
	@DisplayName("Deve retornar error ao salvar no banco de dados")
	public void deveRetornarErrorSalvar() throws JsonProcessingException {
		String catList = objectMapper.writeValueAsString(getContentCat());
		List<Cat> cats = new ArrayList<>();
		BDDMockito.when(buscarRacasGatoConfiguration.getUrl())
				.thenReturn(getUrl());

		BDDMockito.when(buscarRacasGatoConfiguration.buildHeadersAuthentication())
				.thenReturn(getheaders());

		BDDMockito.when(repository.saveAll(Mockito.any()))
				.thenThrow(new InternalError());

		mockServer.when(getResquestExpect())
				.respond(HttpResponse.response()
						.withStatusCode(HttpStatus.OK.value())
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.withBody(catList));
		InternalError internalError = assertThrows(InternalError.class, () -> service.buscaESalva());
		assertThat(internalError).isInstanceOf(InternalError.class);
	}

	@AfterEach
	public void afterAll() {
		clientAndServer.stop();
	}

	private List<ResponseRacaGato> getContentCat() {
		ResponseRacaGato gato = new ResponseRacaGato();
		List<ResponseRacaGato> catList = new ArrayList<>();
		gato.setNome("gatinho");
		gato.setCatID("gat");
		gato.setOrigem("RJ");
		gato.setTemperamento("Active, calm");
		catList.add(gato);
		return catList;
	}

	private String getUrl() {
		return UriComponentsBuilder
				.fromHttpUrl("http://" + LOCALHOST)
				.port(PORT)
				.path(path)
				.toUriString();
	}

	private HttpHeaders getheaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		httpHeaders.set(HttpHeaders.AUTHORIZATION, AUTHORIZATION);
		return httpHeaders;
	}

	private RequestDefinition getResquestExpect() {

		return request()
				.withMethod(HttpMethod.GET.toString())
				.withPath(path)
				.withHeader(HttpHeaders.AUTHORIZATION, AUTHORIZATION)
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
	}


}