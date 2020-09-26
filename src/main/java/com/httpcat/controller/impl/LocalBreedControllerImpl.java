package com.httpcat.controller.impl;

import com.httpcat.controller.LocalBreedController;
import com.httpcat.dto.RacaDTO;
import com.httpcat.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v2")
public class LocalBreedControllerImpl implements LocalBreedController {

	@Autowired
	private CatService service;

	@GetMapping("saveRacas")
	@Override
	public ResponseEntity<List<RacaDTO>> getRacasAndSave() {
		return service.saveRacas();
	}

	@GetMapping("allracas")
	@Override
	public ResponseEntity<List<RacaDTO>> getRacas() {
		return service.getBaseRacas();
	}

	@GetMapping("imagem/oculos")
	@Override
	public ResponseEntity<List<RacaDTO>> getRacasImagenOculos(@RequestParam Map<String, String> query) {
		return service.getAllRacasImagemOculos(query);
	}

	@GetMapping("imagem/chapeus")
	@Override
	public ResponseEntity<List<RacaDTO>> getRacasImagenChapeus() {
		return null;
	}

}
