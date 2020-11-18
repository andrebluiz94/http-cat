package com.httpcat.http.raca.mapper;

import com.httpcat.Entity.Cat;
import com.httpcat.http.raca.dto.ResponseRacaGato;
import com.httpcat.mapper.MapperBase;
import org.springframework.stereotype.Component;

@Component
public class MapperCatEntityParaResponseRacaGato implements MapperBase<Cat , ResponseRacaGato> {

	@Override
	public void map(Cat cat, ResponseRacaGato responseRacaGato) {
		responseRacaGato.setTemperamento(cat.getTemperament());
		responseRacaGato.setOrigem(cat.getOrigin());
		responseRacaGato.setCatID(cat.getId());
		responseRacaGato.setNome(cat.getName());
	}

	@Override
	public ResponseRacaGato map(Cat cat) {
		ResponseRacaGato response = new ResponseRacaGato();
		this.map(cat, response);
		return response;
	}
}
