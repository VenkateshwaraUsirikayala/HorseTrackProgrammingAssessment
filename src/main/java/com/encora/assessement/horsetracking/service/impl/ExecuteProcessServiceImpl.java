package com.encora.assessement.horsetracking.service.impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encora.assessement.horsetracking.constants.AppConstants;
import com.encora.assessement.horsetracking.model.Horse;
import com.encora.assessement.horsetracking.service.BettingService;
import com.encora.assessement.horsetracking.service.CurrencyService;
import com.encora.assessement.horsetracking.service.PayoutDispenseService;
import com.encora.assessement.horsetracking.service.HorseService;
import com.encora.assessement.horsetracking.service.ExecuteProcessService;
import com.encora.assessement.horsetracking.service.WinnerService;
import com.encora.assessement.horsetracking.service.display.DisplayService;

@Service
public class ExecuteProcessServiceImpl implements ExecuteProcessService {

	@Autowired
    HorseService horseService;
	
	@Autowired
	WinnerService winnerService;
	
	@Autowired
    DisplayService displayService;
	
	@Autowired
    PayoutDispenseService dispenseService;
	
	@Autowired
	BettingService bettingService;
	
	@Autowired
	CurrencyService currencyService;
	
    public void processInput(String input) {
        int inputSize = input.length();

        if (inputSize == 0) {
            displayService.displayErrorMessage(AppConstants.INVALID_COMMAND_ERROR);
            return;
        }
        if (inputSize == 1) {
            processExecutionCommand(input);
            return;
        }
        
        
        if (inputSize >= 2&&StringUtils.isAlphanumeric(input)) {
            executeInput(input);
        } else {
            displayService.displayErrorMessage(AppConstants.INVALID_COMMAND_ERROR + input);
        }
    }

    private void processExecutionCommand(String input) {
        String command = Character.toString(input.charAt(0));
        if(command.equalsIgnoreCase("q")) {
        	System.exit(0);
        }else if(command.equalsIgnoreCase("r")) {
        	currencyService.reloadCurrencyInventory();
            displayService.displayCurrencyInventoryAndListOfHorses();
        }else {
        	displayService.displayErrorMessage(AppConstants.INVALID_COMMAND_ERROR + input);
        }
    }

	private void executeInput(String input) {
		String firstChar = Character.toString(input.charAt(0));

		if (firstChar.equalsIgnoreCase("w")) {
			Integer horseNumber = Integer.parseInt(input.substring(1).trim());
			if (horseService.isHorseNumberValid(horseNumber)) {
				winnerService.setWinner(Integer.parseInt(input.substring(1).trim()));
				displayService.displayCurrencyInventoryAndListOfHorses();
			} else {
				displayService.displayErrorMessage(AppConstants.INVALID_COMMAND_ERROR + input);
			}
		} else if (StringUtils.isNumeric(firstChar)) {
			int bettingHorseNumber = Integer.parseInt(firstChar);
			
			Integer betAmount = Integer.parseInt(input.substring(1).trim());
			
			boolean betApplied = bettingService.applyBet(bettingHorseNumber, betAmount);
			if (betApplied) {
				Integer winningHorseNumber = winnerService.getWinner();
				if (winningHorseNumber == bettingHorseNumber) {

					displayService.displayCurrencyInventoryAndListOfHorses();
					
					Integer bet = bettingService.retrieveBet().getBet();
					Horse winningHorse = horseService.getHorses().get(winningHorseNumber - 1);
					Integer odds = winningHorse.getOdds();
					
					String totalCashPayout = "Payout: " + winningHorse.getName() + ", $" + odds * bet;

					String cashDispenseDenominationResult = dispenseService.dispenseWinningsPayout(bet * odds);

					if (StringUtils.isNotEmpty(cashDispenseDenominationResult)) {
						displayService.displayMessage(totalCashPayout);
						displayService.displayMessage(cashDispenseDenominationResult);
					} else {
						displayService.displayErrorMessage(AppConstants.INSUFFICIENT_FUNDS_ERROR + bet * odds);
					}
				} else {
					Horse losingHorse = horseService.getHorses().get(bettingHorseNumber - 1);
					displayService.displayErrorMessage(AppConstants.NO_PAYOUT_ERROR + losingHorse.getName());
				}
			} else {
				displayService.displayErrorMessage(AppConstants.INVALID_BET_ERROR + input);
			}
		}
	}
  
}
