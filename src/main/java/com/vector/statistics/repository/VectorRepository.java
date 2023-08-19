package com.vector.statistics.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vector.statistics.entity.Vector;

@Repository
public interface VectorRepository extends CrudRepository<Vector, Integer> {

}
