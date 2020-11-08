package com.httpcat.http.raca.mapper;

import com.httpcat.Entity.Cat;
import com.httpcat.http.raca.dto.ResponseRacaGato;
import com.httpcat.mapper.MapperBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
		MapperResponseRacaGatoParaEntityCat.class
})
public class MapperResponseRacaGatoParaEntityCatTest {

	@Autowired
	MapperBase<ResponseRacaGato, Cat> mapperBase;

	@Test
	@DisplayName("Deve converter gato response para Entidade gato")
	public void deveConverterResponseRacaGatoParaEntityCat() {

		ResponseRacaGato gatoResponse = getGatoResponse();

		Cat cat = mapperBase.map(gatoResponse);

		assertEquals(gatoResponse.getTemperamento(), cat.getTemperament());
		assertEquals(gatoResponse.getOrigem(), cat.getOrigin());
		assertEquals(gatoResponse.getNome(), cat.getName());
		assertEquals(gatoResponse.getCatID(), cat.getId());

	}

	private ResponseRacaGato getGatoResponse() {
		ResponseRacaGato gatoResponse = new ResponseRacaGato();
		gatoResponse.setTemperamento("Active");
		gatoResponse.setOrigem("Brasil");
		gatoResponse.setCatID("meCat");
		gatoResponse.setNome("Meu gato");
		return gatoResponse;
	}
}