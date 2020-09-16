package com.httpcat.repository;

import com.httpcat.Entity.Cat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface racasRepository extends CrudRepository<Cat, Long> {

}
