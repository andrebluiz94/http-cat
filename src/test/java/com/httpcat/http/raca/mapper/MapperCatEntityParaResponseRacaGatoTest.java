package com.httpcat.http.raca.mapper;

import com.httpcat.Entity.Cat;
import com.httpcat.http.raca.dto.ResponseRacaGato;
import com.httpcat.mapper.MapperBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
		MapperCatEntityParaResponseRacaGato.class
})
public class MapperCatEntityParaResponseRacaGatoTest {

	@Autowired
	private MapperBase<Cat, ResponseRacaGato> mapperBase;

	@Test
	public void deveConverterCatParaResponseRacaGato() {
		Cat cat = getCat();
		ResponseRacaGato response = mapperBase.map(cat);

		assertEquals(getCat().getId(), response.getCatID());
		assertEquals(getCat().getTemperament(), response.getTemperamento());
		assertEquals(getCat().getOrigin(), response.getOrigem());
		assertEquals(getCat().getName(), response.getNome());
	}

	private Cat getCat() {
		Cat cat = new Cat();
		cat.setIdCat(1L);
		cat.setId("my");
		cat.setName("MyCat");
		cat.setOrigin("Brasil");
		cat.setTemperament("Active");
		return cat;
	}
}