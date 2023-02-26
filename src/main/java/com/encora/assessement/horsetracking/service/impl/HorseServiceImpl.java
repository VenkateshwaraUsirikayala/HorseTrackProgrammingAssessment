package com.encora.assessement.horsetracking.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encora.assessement.horsetracking.constants.AppConstants;
import com.encora.assessement.horsetracking.model.Horse;
import com.encora.assessement.horsetracking.repository.HorseRepository;
import com.encora.assessement.horsetracking.service.HorseService;

@Service
public class HorseServiceImpl implements HorseService {

	@Autowired
	private HorseRepository horseRepository;

	@Override
	public List<Horse> getHorses() {
		List<Horse> horsesList = new ArrayList<>();
		Iterable<Horse> horses = horseRepository.findAll();
		horses.forEach(horsesList::add);
		return horsesList;
	}

	@Override
	public void displayHorses() {
		List<Horse> horses = getHorses();
		StringBuilder sb = new StringBuilder();
		sb.append("Horses:\n");

		for (Horse horse : horses) {
			sb.append(horse.getId() + ", " + horse.getName() + ", " + horse.getOdds() + ", ");
			sb.append(horse.isWon() ? AppConstants.WON + AppConstants.LINE_SEPARATOR
					: AppConstants.LOST + AppConstants.LINE_SEPARATOR);
		}
		System.out.println(sb.toString().trim());
	}

	@Override
	public boolean isHorseNumberValid(Integer horseNumber) {
		return horseNumber >= 1 && horseNumber <= getHorses().size() ? true : false;
	}

}
