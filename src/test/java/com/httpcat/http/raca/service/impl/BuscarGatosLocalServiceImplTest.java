package com.httpcat.http.raca.service.impl;

import com.httpcat.Entity.Cat;
import com.httpcat.http.raca.service.BuscarGatosLocalService;
import com.httpcat.repository.RacasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class BuscarGatosLocalServiceImplTest {

	private BuscarGatosLocalService service;

	@MockBean
	private RacasRepository repository;

	@BeforeEach
	void setUp() {
		this.service = new BuscarGatosLocalServiceImpl(repository);
	}

	@Test
	@DisplayName("Deve retornar todos os gatos da base de dados")
	public void deveRetornarTodosCats() {

		List<Cat> catList = getCatList();
		BDDMockito.when(repository.findAll())
				.thenReturn(catList);

		Cat cats = Objects.requireNonNull(service.getCats().getBody()).get(0);
		assertNotNull(cats);
		assertEquals(getCat().getId(), cats.getId());
		assertEquals(getCat().getIdCat(), cats.getIdCat());
		assertEquals(getCat().getName(), cats.getName());
		assertEquals(getCat().getOrigin(), cats.getOrigin());
		assertEquals(getCat().getTemperament(), cats.getTemperament());
	}

	@Test
	@DisplayName("Deve retornar NO_CONTENT(204) caso não tenha nenhum gato na base de dados")
	public void deveRetornaNoContent() {

		BDDMockito.when(repository.findAll())
				.thenThrow(new ResponseStatusException(HttpStatus.NO_CONTENT));

		ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
				() -> service.getCats().getStatusCode());

		assertThat(responseStatusException).isInstanceOf(ResponseStatusException.class);
		assertEquals(HttpStatus.NO_CONTENT, responseStatusException.getStatus());
	}

	@Test
	@DisplayName("Deve atraves do nome da raça buscar o gato")
	public void deveBuscarGatoPeloNome() {

		Cat gato = getCat();

		BDDMockito.when(repository.findByName(Mockito.any()))
				.thenReturn(Optional.of(gato));

		Cat catByName = service.getCatByName(gato.getName()).getBody().get();
		assertNotNull(catByName);
		assertEquals(gato.getId(), catByName.getId());
		assertEquals(gato.getTemperament(), catByName.getTemperament());
		assertEquals(gato.getOrigin(), catByName.getOrigin());
		assertEquals(gato.getName(), catByName.getName());
		assertEquals(gato.getIdCat(), catByName.getIdCat());

	}

	@Test
	@DisplayName("Deve retornar NO_CONTENT(204) caso não tenha nenhum gato na base de dados")
	void deveRetornarNoContent() {

		String nome = "anyString";

		BDDMockito.when(repository.findByName(Mockito.anyString()))
				.thenThrow(new ResponseStatusException(HttpStatus.NO_CONTENT));

		ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
				() -> service.getCatByName(nome).getStatusCode());

		assertThat(responseStatusException).isInstanceOf(ResponseStatusException.class);
		assertEquals(HttpStatus.NO_CONTENT, responseStatusException.getStatus());
	}

	@Test
	@DisplayName("Deve buscar gatos pelo temperamento")
	void deveBuscarGatoPorTemperamento() {

		Cat gato = getCat();
		List<Cat> catList = new ArrayList<>();
		catList.add(gato);

		BDDMockito.when(repository.findAll())
				.thenReturn(catList);

		Cat catByName = Objects.requireNonNull(service.getCatByTemperamento(gato.getTemperament())
				.getBody())
				.stream()
				.findAny()
				.get();

		assertNotNull(catByName);
		assertEquals(gato.getId(), catByName.getId());
		assertEquals(gato.getTemperament(), catByName.getTemperament());
		assertEquals(gato.getOrigin(), catByName.getOrigin());
		assertEquals(gato.getName(), catByName.getName());
		assertEquals(gato.getIdCat(), catByName.getIdCat());

	}

	@Test
	@DisplayName("Deve retornar NO_CONTENT(204) caso não tenha nenhum gato na base de dados, com o temperamento buscado")
	void deveRetornarNoContentCaseNaoTenhaGatoComTemperamentoBuscado() {

		String nome = "anyString";

		BDDMockito.when(repository.findAll())
				.thenThrow(new ResponseStatusException(HttpStatus.NO_CONTENT));

		ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
				() -> service.getCatByTemperamento(nome).getStatusCode());

		assertThat(responseStatusException).isInstanceOf(ResponseStatusException.class);
		assertEquals(HttpStatus.NO_CONTENT, responseStatusException.getStatus());
	}

	@Test
	@DisplayName("Deve buscar uma lista de gatos")
	public void deveBuscarUmaListaDeGatos() {
		List<String> nomeGatoList = new ArrayList<>();
		List<Cat> catList = new ArrayList<>();

		Cat gato = getCat();
		catList.add(gato);
		catList.add(gato);
		catList.add(gato);

		String nomeGato = gato.getName();
		String nomeGatoUm = gato.getName();
		String nomeGatoDois = gato.getName();

		nomeGatoList.add(nomeGato);
		nomeGatoList.add(nomeGatoUm);
		nomeGatoList.add(nomeGatoDois);

		BDDMockito.when(repository.findAll())
				.thenReturn(catList);

		List<Cat> catByName = Objects.requireNonNull(service.getCatsList(nomeGatoList)
				.getBody());

		assertNotNull(catByName);
		assertEquals(nomeGatoList.size(), catByName.size());
		assertEquals(gato.getId(), catByName.get(0).getId());
		assertEquals(gato.getTemperament(), catByName.get(0).getTemperament());
		assertEquals(gato.getOrigin(), catByName.get(0).getOrigin());
		assertEquals(gato.getName(), catByName.get(0).getName());
		assertEquals(gato.getIdCat(), catByName.get(0).getIdCat());

	}

	@Test
	@DisplayName("Deve retornar NO_CONTENT(204) caso não tenha nenhum gato na base de dados, caso não tenha gatos da lista")
	public void deveRetornarNoContentCaseNaoTenhaGatoComListaNomeGato() {

		List<String> nomeGatoList = new ArrayList<>();
		List<Cat> catList = new ArrayList<>();

		Cat gato = getCat();
		catList.add(gato);
		catList.add(gato);
		catList.add(gato);

		String nomeGato = gato.getName();
		String nomeGatoUm = gato.getName();
		String nomeGatoDois = gato.getName();

		nomeGatoList.add(nomeGato);
		nomeGatoList.add(nomeGatoUm);
		nomeGatoList.add(nomeGatoDois);

		BDDMockito.when(repository.findAll())
				.thenThrow(new ResponseStatusException(HttpStatus.NO_CONTENT));

		ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
				() -> service.getCatsList(nomeGatoList).getStatusCode());

		assertThat(responseStatusException).isInstanceOf(ResponseStatusException.class);
		assertEquals(HttpStatus.NO_CONTENT, responseStatusException.getStatus());
	}

	private List<Cat> getCatList() {
		List<Cat> catList = new ArrayList<>();

		Cat catUm = getCat();
		Cat catDois = getCat();
		catList.add(catUm);
		catList.add(catDois);
		return catList;
	}

	private Cat getCat() {
		Cat cat = new Cat();
		cat.setId("cat");
		cat.setTemperament("Active");
		cat.setOrigin("Brasil");
		cat.setName("CatSon");
		cat.setIdCat(1L);
		return cat;
	}

	@Test
	@DisplayName("Deve retornar 204 quando buscar na base de dados e não encontrar todos os gatos")
	void deveRetonarStatus204QuandoNaoTiverConteudoNaBaseDados() {
		BDDMockito.when(repository.findAll())
				.thenReturn(null);


		ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class, () -> service.getCats());
		assertThat(responseStatusException).isInstanceOf(ResponseStatusException.class);
		assertEquals(HttpStatus.NO_CONTENT ,responseStatusException.getStatus());
	}

	@Test
	@DisplayName("Deve retornar 204 quando buscar na base de dados quando buscar pelo nome")
	void deveRetonarStatus204QuandoNaoTiverConteudoNaBaseDadosQuandoBuscarPeloNome() {
		String anyString = "aloha";
		BDDMockito.when(repository.findByName(Mockito.anyString()))
				.thenReturn(null);


		ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class, () -> service.getCatByName(anyString));
		assertThat(responseStatusException).isInstanceOf(ResponseStatusException.class);
		assertEquals(HttpStatus.NO_CONTENT ,responseStatusException.getStatus());
	}

	@Test
	@DisplayName("Deve retornar 204 quando buscar na base de dados quando buscar pelo temperamento")
	void deveRetonarStatus204QuandoNaoTiverConteudoNaBaseDadosQuandoBuscarPeloTemperamento() {
		String anyString = "aloha";
		BDDMockito.when(repository.findAll())
				.thenReturn(null);

		ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class, () -> service.getCatByTemperamento(anyString));
		assertThat(responseStatusException).isInstanceOf(ResponseStatusException.class);
		assertEquals(HttpStatus.NO_CONTENT ,responseStatusException.getStatus());
	}

	@Test
	@DisplayName("Deve retornar 204 quando buscar na base de dados quando buscar pelo lista de racas")
	void deveRetonarStatus204QuandoNaoTiverConteudoNaBaseDadosQuandoBuscarPorUmaListaRacas() {
		List<String> catList = new ArrayList<>();
		catList.add("aloha");
		catList.add("aloha");
		BDDMockito.when(repository.findAll())
				.thenReturn(null);

		ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class, () -> service.getCatsList(catList));
		assertThat(responseStatusException).isInstanceOf(ResponseStatusException.class);
		assertEquals(HttpStatus.NO_CONTENT ,responseStatusException.getStatus());
	}
}