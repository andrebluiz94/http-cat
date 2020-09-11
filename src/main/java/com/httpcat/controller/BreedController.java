package com.httpcat.controller;

import com.httpcat.dto.RacaDto;
import org.springframework.http.ResponseEntity;

public interface BreedController {
	ResponseEntity<RacaDto[]> getRacas();
}
