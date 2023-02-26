package com.encora.assessement.horsetracking.service;

import com.encora.assessement.horsetracking.model.Bet;

public interface BettingService {

	Bet retrieveBet();

	boolean applyBet(Integer horse, Integer bet);

}