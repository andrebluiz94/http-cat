package com.httpcat.controller;

import com.httpcat.dto.RacaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface BreedController {
	ResponseEntity<RacaDto[]> getRacas();
	public ResponseEntity<RacaDto[]> getRaca(@RequestParam Map<String,String> raca);
}
