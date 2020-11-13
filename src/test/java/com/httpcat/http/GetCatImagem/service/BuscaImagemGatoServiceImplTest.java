package com.httpcat.http.GetCatImagem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.httpcat.Entity.Cat;
import com.httpcat.http.GetCatImagem.config.BuscaImagemGatoConfiguration;
import com.httpcat.http.GetCatImagem.dto.RequestGatoImagem;
import com.httpcat.http.GetCatImagem.dto.ResponseRacaGatoImagem;
import com.httpcat.http.GetCatImagem.dto.categorioes;
import com.httpcat.http.raca.dto.ResponseRacaGato;
import com.httpcat.repository.RacasRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.KeyMatchStyle;
import org.mockserver.model.Parameters;
import org.mockserver.model.RequestDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.Parameter.param;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
		RestTemplate.class,
		ObjectMapper.class,
		BuscaImagemGatoServiceImpl.class
})
public class BuscaImagemGatoServiceImplTest {

	@Autowired
	private BuscaImagemGatoService service;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private BuscaImagemGatoConfiguration configuration;

	@MockBean
	private RacasRepository repository;

	private ClientAndServer clientAndServer;
	private MockServerClient mockServer;


	private static final String path ="/images/search";
	private static final String AUTHORIZATION = "fake-token";
	private static final String LOCALHOST = "localhost";
	private static final int PORT = 9090;

	@BeforeEach
	void setUp() {
		clientAndServer = ClientAndServer.startClientAndServer(PORT);
		mockServer = new MockServerClient(LOCALHOST, PORT);
	}

	@Test
	public void deveRetornarListaDeImagemDeGatos() throws JsonProcessingException {

		String getReturn = objectMapper.writeValueAsString(getImagensGatos());

		BDDMockito.when(configuration.getUrl())
				.thenReturn(getUrlBuilder().toUriString());

		BDDMockito.when(configuration.buildHeadersAuthentication())
				.thenReturn(getheaders());


		BDDMockito.when(repository.findAll())
				.thenReturn(getCatList());


		mockServer.when(getResquestExpect())
				.respond(HttpResponse.response()
						.withStatusCode(HttpStatus.OK.value())
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.withBody(getReturn));

		List<Optional<ResponseRacaGatoImagem>> buscar = service.buscar(getRequest());
		ResponseRacaGatoImagem responseRacaGatoImagem = buscar.get(0).get();
		ResponseRacaGatoImagem imagemGatosEntity = getImagensGatos().get(0);

		Assertions.assertEquals(responseRacaGatoImagem.getUrl(), imagemGatosEntity.getUrl());
		Assertions.assertEquals(responseRacaGatoImagem.getIdRaca(), imagemGatosEntity.getIdRaca());
		Assertions.assertEquals(responseRacaGatoImagem.getCategoria().get(0).getNome(),
				imagemGatosEntity.getCategoria().get(0).getNome());
	}

	@Test
	@DisplayName("Deve retornar error se a api cats estiver com problema")
	public void deveRetornarErrorAoBuscarNaAPICats() {

		BDDMockito.when(configuration.getUrl())
				.thenReturn(getUrlBuilder().toUriString());

		BDDMockito.when(configuration.buildHeadersAuthentication())
				.thenReturn(getheaders());


		BDDMockito.when(repository.findAll())
				.thenReturn(getCatList());

		mockServer.when(getResquestExpect())
				.respond(HttpResponse.response()
						.withStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

		ResponseStatusException internalError = assertThrows(ResponseStatusException.class, () -> service.buscar(getRequest()));
		assertThat(internalError).isInstanceOf(ResponseStatusException.class);
	}

	public List<ResponseRacaGatoImagem> getImagensGatos(){
		List<ResponseRacaGatoImagem> catImagemList = new ArrayList<>();
		ResponseRacaGatoImagem catImagem = new ResponseRacaGatoImagem();

		catImagem.setIdRaca("catcat");
		catImagem.setCategoria(getCategoria());
		catImagem.setUrl("MinhaSkil.com");
		catImagem.setRaca(getCatRaca());

		catImagemList.add(catImagem);
		return catImagemList;
	}

	private List<categorioes> getCategoria() {
		List<categorioes> categorioesList = new ArrayList<>();
		categorioes categoria = new categorioes();
		categorioesList.add(categoria);
		return categorioesList;
	}

	private List<ResponseRacaGato> getCatRaca() {
		List<ResponseRacaGato> catRaca = new ArrayList<>();
		catRaca.add(getGato());
		return catRaca;
	}

	private ResponseRacaGato getGato() {
		ResponseRacaGato raca = new ResponseRacaGato();
		raca.setNome("My Cat");
		raca.setCatID("cat");
		raca.setOrigem("Brasil");
		raca.setTemperamento("Active");
		return raca;
	}

	private RequestGatoImagem getRequest() {
		RequestGatoImagem catImagem = new RequestGatoImagem();
		Map<String, String> expand = new HashMap<>();
		expand.put("raca", "myCat");
		catImagem.setExpand(expand);
		return catImagem;
	}

	private List<Cat> getCatList() {
		List<Cat> catList = new ArrayList<>();
		Cat gatoUm = getCat();
		catList.add(gatoUm);
		return catList;
	}

	private Cat getCat() {
		return Cat
				.builder()
				.idCat(1L)
				.id("cat")
				.name("MyCat")
				.origin("Brasil")
				.temperament("Active")
				.build();
	}

	@AfterEach
	public void afterAll() {
		clientAndServer.stop();
	}

	private UriComponentsBuilder getUrlBuilder() {
		MultiValueMap<String, String> expand = new HttpHeaders();

		expand.add("breed_id", getCat().getName());
		expand.add("category_ids", getCat().getIdCat().toString());
		expand.add("limit","3");
		expand.add("page", "1");
		String HTTP = "http://";

		return UriComponentsBuilder
				.fromHttpUrl(HTTP + LOCALHOST)
				.path(path)
				.port(PORT)
				.queryParams(expand);
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
				.withPath(this.path)
				.withQueryStringParameters(new Parameters(
						param("breed_id",getCat().getName()),
						param("category_ids",getCat().getIdCat().toString()),
						param("limit","3"),
						param("page","1")
				).withKeyMatchStyle(KeyMatchStyle.SUB_SET))
				.withHeader(HttpHeaders.AUTHORIZATION, AUTHORIZATION)
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
	}
}