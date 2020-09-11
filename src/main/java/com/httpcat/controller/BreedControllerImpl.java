package com.httpcat.controller;

import com.httpcat.dto.RacaDto;
import com.httpcat.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class BreedControllerImpl implements BreedController {


	@Autowired
	private CatService service;

	@GetMapping("/racas")
	@Override
	public ResponseEntity<RacaDto[]> getRacas() {
		return service.getAllRacas();
	}

	@GetMapping("busca")
	public ResponseEntity<RacaDto[]> getRaca(@RequestParam String raca){
		return service.getRaca(raca);
	}

}
