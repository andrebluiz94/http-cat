package com.httpcat.http.raca.service;

import com.httpcat.Entity.Cat;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BuscarGatosLocalService {

    ResponseEntity<List<Cat>> getCats();

    ResponseEntity<Optional<Cat>> getCatByName(String nome);

    ResponseEntity<List<Cat>> getCatByTemperamento(String temperamento);

    ResponseEntity<List<Cat>> getCatsList(List<String> racas);

}
