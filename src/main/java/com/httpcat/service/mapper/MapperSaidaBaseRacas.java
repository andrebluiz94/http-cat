package com.httpcat.service.mapper;

import com.httpcat.Entity.Cat;
import com.httpcat.dto.RacaDTO;
import com.httpcat.mapper.MapperBase;
import org.springframework.stereotype.Component;

@Component
public class MapperSaidaBaseRacas implements MapperBase<Cat, RacaDTO> {
	@Override
	public void map(Cat cat, RacaDTO racaDto) {
		racaDto.setTemperament(cat.getTemperament());
		racaDto.setOrigin(cat.getOrigin());
		racaDto.setName(cat.getName());
		racaDto.setId(cat.getId());
	}

	@Override
	public RacaDTO map(Cat cat) {
		RacaDTO raca = new RacaDTO();
		this.map(cat, raca);
		return raca;
	}
}
