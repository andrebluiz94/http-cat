package com.httpcat.service.mapper;


import com.httpcat.Entity.Cat;
import com.httpcat.dto.RacaDTO;
import com.httpcat.mapper.MapperBase;
import org.springframework.stereotype.Component;


@Component
public class MapperEntradaBaseRacas implements MapperBase<RacaDTO, Cat> {
	@Override
	public void map(RacaDTO racaDto, Cat cat) {
		cat.setId(racaDto.getId());
		cat.setName(racaDto.getName());
		cat.setOrigin(racaDto.getOrigin());
		cat.setTemperament(racaDto.getTemperament());
	}

	@Override
	public Cat map(RacaDTO racaDto) {
		Cat cat = new Cat();
		this.map(racaDto, cat);
		return cat;
	}
}
