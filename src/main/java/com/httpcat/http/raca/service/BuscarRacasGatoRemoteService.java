package com.httpcat.http.raca.service;

import com.httpcat.http.raca.dto.ResponseRacaGato;

import java.util.List;

public interface BuscarRacasGatoRemoteService {
	List<ResponseRacaGato> buscar();

	List<ResponseRacaGato> buscaESalva();
}
