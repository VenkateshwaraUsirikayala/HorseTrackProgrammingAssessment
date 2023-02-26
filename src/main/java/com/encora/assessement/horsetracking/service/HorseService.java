package com.encora.assessement.horsetracking.service;

import java.util.List;

import com.encora.assessement.horsetracking.model.Horse;

public interface HorseService{
	
    public List<Horse> getHorses();
    public void displayHorses();
	boolean isHorseNumberValid(Integer horseNumber);

}
