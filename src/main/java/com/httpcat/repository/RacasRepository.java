package com.httpcat.repository;

import com.httpcat.Entity.Cat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RacasRepository extends JpaRepository<Cat, Long> {
    Optional<Cat> findByName(String name);
}
