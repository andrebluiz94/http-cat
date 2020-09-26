package com.httpcat.controller.impl;

import com.httpcat.controller.BreedController;
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
@RequestMapping("api/v1")
public class BreedControllerImpl implements BreedController {

	@Autowired
	private CatService service;

	//busca all classes
	@GetMapping("/racas")
	@Override
	public ResponseEntity<List<RacaDTO>> getRacas() {
		return service.getAllRacas();
	}

	//busca pela pela raça
	@GetMapping("busca")
	@Override
	public ResponseEntity<List<RacaDTO>> getRaca(@RequestParam Map<String, String> raca) {
		return service.getRaca(raca);
	}

	//origem
	@GetMapping("busca_origem")
	public ResponseEntity<List<RacaDTO>> getRaca(@RequestParam("origem") String raca2) {
		return service.getRaca(raca2);
	}
}
