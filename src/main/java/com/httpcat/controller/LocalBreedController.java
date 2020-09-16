package com.httpcat.controller;

import com.httpcat.dto.RacaDto;
import org.springframework.http.ResponseEntity;

public interface LocalBreedController {
	ResponseEntity<RacaDto[]> getRacasAndSave();
}
