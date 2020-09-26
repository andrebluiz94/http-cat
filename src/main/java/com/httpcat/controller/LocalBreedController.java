package com.httpcat.controller;

import com.httpcat.dto.RacaDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface LocalBreedController {
	ResponseEntity<List<RacaDTO>> getRacasAndSave();
	ResponseEntity<List<RacaDTO>> getRacas();
	ResponseEntity<List<RacaDTO>> getRacasImagenOculos(Map<String, String> query);
	ResponseEntity<List<RacaDTO>> getRacasImagenChapeus();
}
