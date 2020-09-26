package com.httpcat.controller;

import com.httpcat.dto.RacaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface BreedController {
	ResponseEntity<List<RacaDTO>> getRacas();

}
