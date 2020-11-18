package com.httpcat.http.raca.service;

import com.httpcat.http.raca.dto.ResponseRacaGato;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BuscarRacasGatoRemoteService {
	List<ResponseRacaGato> buscar();

	ResponseEntity<List<ResponseRacaGato>> buscaESalva();
}
