package com.httpcat.service.mapper;


import com.httpcat.Entity.Cat;
import com.httpcat.dto.RacaDto;
import com.httpcat.mapper.MapperBase;



public class MapperEntradaBaseRacas implements MapperBase<RacaDto, Cat> {
	@Override
	public void map(RacaDto racaDto, Cat cat) {

	}

	@Override
	public Cat map(RacaDto racaDto) {
		Cat cat = new Cat();
		this.map(racaDto, cat);
		return cat;
	}
}
