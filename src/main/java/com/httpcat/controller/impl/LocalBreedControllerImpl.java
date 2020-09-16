package com.httpcat.controller.impl;

import com.httpcat.client.impl.ClientRacasImpl;
import com.httpcat.controller.LocalBreedController;
import com.httpcat.dto.RacaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class LocalBreedControllerImpl implements LocalBreedController {

	@Autowired
	private ClientRacasImpl clientRacas;

	@Override
	public ResponseEntity<RacaDto[]> getRacasAndSave() {
		return null;
	}
}
