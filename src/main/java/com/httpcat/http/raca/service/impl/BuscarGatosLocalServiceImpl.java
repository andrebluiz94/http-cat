package com.httpcat.http.raca.service.impl;

import com.httpcat.Entity.Cat;
import com.httpcat.http.raca.service.BuscarGatosLocalService;
import com.httpcat.repository.RacasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class BuscarGatosLocalServiceImpl implements BuscarGatosLocalService {


    private final RacasRepository repository;

    @Autowired
    public BuscarGatosLocalServiceImpl(RacasRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<List<Cat>> getCats() {
        List<Cat> catList = Optional.of(repository.findAll())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));

        return ResponseEntity.ok(catList);
    }

    @Override
    public ResponseEntity<Optional<Cat>> getCatByName(String nome) {
        Optional<Cat> cat = Optional.ofNullable(repository.findByName(nome))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(cat);
    }

    @Override
    public ResponseEntity<List<Cat>> getCatByTemperamento(String temperamento) {
        String regex = String.format(".*%s.*", temperamento);
        List<Cat> catList = Optional.of(
                repository
                        .findAll()
                        .stream()
                        .filter(raca -> raca.getTemperament().matches(regex))
                        .collect(Collectors.toList())).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(catList);
    }

    @Override
    public ResponseEntity<List<Cat>> getCatsList(List<String> racas) {
        List<Cat> catList = Optional.of(
                repository
                        .findAll()
                        .stream()
                        .filter(cat -> racas.contains(cat.getName()))
                        .collect(Collectors.toList())).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(catList);
    }
}
