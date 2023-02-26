package com.encora.assessement.horsetracking.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encora.assessement.horsetracking.model.Horse;
import com.encora.assessement.horsetracking.repository.HorseRepository;
import com.encora.assessement.horsetracking.service.WinnerService;

@Service
public class WinnerServiceImpl implements WinnerService {

	@Autowired
	private HorseRepository horseRepository;

	@Override
	public void setWinner(Integer key) {

		Optional<Horse> findByWon = horseRepository.findByWon(true);
		Horse horse2 = findByWon.get();
		horse2.setWon(false);
		horseRepository.save(horse2);

		Optional<Horse> optionalHorse = horseRepository.findById(key);

		if (optionalHorse.isEmpty()) {
			throw new RuntimeException("No Horse Id exists with that key");
		}
		Horse horse = optionalHorse.get();
		horse.setWon(true);
		horseRepository.save(horse);
	}

	@Override
	public Integer getWinner() {

		Integer winningHorseNumber = -1;
		Iterable<Horse> findAll = horseRepository.findAll();

		for (Horse horse : findAll) {
			if (horse.isWon()) {
				winningHorseNumber = horse.getId();
				break;
			}
		}
		return winningHorseNumber;
	}

}
