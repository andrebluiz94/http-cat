package com.httpcat.service;

import com.httpcat.dto.RacaDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CatService {
	ResponseEntity<List<RacaDTO>> saveRacas();
	ResponseEntity<List<RacaDTO>> getBaseRacas();
	ResponseEntity<List<RacaDTO>> getRaca(String raca);
	ResponseEntity<List<RacaDTO>> getAllRacas();
	ResponseEntity<List<RacaDTO>> getRaca(Map<String, String> raca);
	ResponseEntity<List<RacaDTO>> getAllRacasImagemOculos(Map<String, String> racaQuery);
	ResponseEntity<List<RacaDTO>> getAllRacasImagemChapeu();

}
