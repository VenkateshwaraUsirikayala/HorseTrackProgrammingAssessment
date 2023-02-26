package com.encora.assessement.horsetracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encora.assessement.horsetracking.service.CurrencyService;
import com.encora.assessement.horsetracking.service.HorseService;
import com.encora.assessement.horsetracking.service.display.DisplayService;

import lombok.extern.slf4j.Slf4j;

@Service
public class DisplayServiceImpl implements DisplayService{
	
	@Autowired
	CurrencyService currencyService;
	
	@Autowired
    HorseService horseService;

    @Override
    public void displayInventory() {
    	currencyService.displayCurrencyInventory();
    }

    @Override
    public void displayHorses() {
        horseService.displayHorses();
    }

    @Override
    public void displayErrorMessage(String errorMessage) {
    	System.out.println(errorMessage);
        displayCurrencyInventoryAndListOfHorses();
    }

    @Override
    public void displayMessage(String message) {
    	System.out.println(message);
    }


}
