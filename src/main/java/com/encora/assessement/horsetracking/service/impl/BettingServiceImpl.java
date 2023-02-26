package com.encora.assessement.horsetracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encora.assessement.horsetracking.constants.AppConstants;
import com.encora.assessement.horsetracking.model.Bet;
import com.encora.assessement.horsetracking.service.BettingService;
import com.encora.assessement.horsetracking.service.HorseService;
import com.encora.assessement.horsetracking.service.display.DisplayService;

@Service
public class BettingServiceImpl implements BettingService{
	
	@Autowired
	private HorseService horseService;
	
	@Autowired
	private DisplayService displayService;
	
    private static Bet bet = new Bet(1,0, 0);

	@Override
	public Bet retrieveBet() {
	    return bet;
	}

	@Override
	public boolean applyBet(Integer horseNumber, Integer betAmount) {

		if (!horseService.isHorseNumberValid(horseNumber)) {
			displayService
					.displayErrorMessage(AppConstants.INVALID_HORSE_NUMBER_ERROR + horseNumber);
			return false;
		}
		
	    bet.setHorse(horseNumber);
	    bet.setBet(betAmount);
		return true;
	}

}