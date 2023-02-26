package com.encora.assessement.horsetracking.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.encora.assessement.horsetracking.model.Horse;

public interface HorseRepository extends CrudRepository<Horse, Integer> {

	Optional<Horse> findByWon(boolean won);
	
}
