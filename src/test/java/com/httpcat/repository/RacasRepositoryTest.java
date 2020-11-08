package com.httpcat.repository;

import com.httpcat.Entity.Cat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
class RacasRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private RacasRepository repository;

	@Test
	@DisplayName("Deve retornar os gatos pelo nome")
	public void deveRetonarGatosPeloNome() {

		Cat cat = Cat
				.builder()
				.temperament("Calmo")
				.origin("Brasil")
				.name("Meu gato")
				.id("cat")
				.build();
		entityManager.persist(cat);

		Optional<Cat> resultBusca = repository.findByName(cat.getName());

		assertNotNull(resultBusca);
		assertEquals(cat.getId(), cat.getId());
		assertEquals(cat.getName(), cat.getName());
		assertEquals(cat.getOrigin(), cat.getOrigin());
		assertEquals(cat.getTemperament(), cat.getTemperament());
	}
}