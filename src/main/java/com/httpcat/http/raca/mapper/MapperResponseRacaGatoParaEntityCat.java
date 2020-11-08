package com.httpcat.http.raca.mapper;

import com.httpcat.Entity.Cat;
import com.httpcat.http.raca.dto.ResponseRacaGato;
import com.httpcat.mapper.MapperBase;
import org.springframework.stereotype.Component;

@Component
public class MapperResponseRacaGatoParaEntityCat implements MapperBase<ResponseRacaGato, Cat> {

	@Override
	public void map(ResponseRacaGato responseRacaGato, Cat cat) {
		cat.setId(responseRacaGato.getCatID());
		cat.setName(responseRacaGato.getNome());
		cat.setOrigin(responseRacaGato.getOrigem());
		cat.setTemperament(responseRacaGato.getTemperamento());
	}

	@Override
	public Cat map(ResponseRacaGato responseRacaGato) {
		Cat cat = new Cat();
		this.map(responseRacaGato, cat);
		return cat;
	}
}
