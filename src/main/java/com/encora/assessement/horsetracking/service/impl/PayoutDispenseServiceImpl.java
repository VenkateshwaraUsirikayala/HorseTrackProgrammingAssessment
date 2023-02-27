package com.encora.assessement.horsetracking.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encora.assessement.horsetracking.constants.AppConstants;
import com.encora.assessement.horsetracking.model.Currency;
import com.encora.assessement.horsetracking.service.CurrencyService;
import com.encora.assessement.horsetracking.service.PayoutDispenseService;
import com.encora.assessement.horsetracking.service.display.DisplayService;

@Service
public class PayoutDispenseServiceImpl implements PayoutDispenseService {

	@Autowired
	DisplayService displayService;

	@Autowired
	CurrencyService currencyServiceImpl;

	public String dispenseWinningsPayout(Integer payout) {

		Currency currency = currencyServiceImpl.getCurrency();

		int balance = currencyServiceImpl.getTotalCashOnHand();

		if (payout > balance) {
			return "";
		}
		
		int[] coins = {currency.getHundred(), currency.getTwenty(), currency.getTen(), currency.getFive(), currency.getOne()};
		
		int[] denominations = {100, 20, 10, 5, 1};
		
		int[] result = new int[5];
		
	    for (int i = 0; i < denominations.length; i++) {
            int numCoins = Math.min(payout / denominations[i], coins[i]); 
            result[i] = numCoins; 
            payout -= numCoins * denominations[i];
        }
	    
	    if(payout!=0) {
	    	return StringUtils.EMPTY;
	    }
	    
		int hundred = result[0];
		int twenty = result[1];
		int ten = result[2];
		int five = result[3];
		int one = result[4];
		
		if (currency.getHundred() >= hundred) {
			currency.setHundred(currency.getHundred() - hundred);
		}
		if (currency.getTwenty() >= twenty) {
			currency.setTwenty(currency.getTwenty() - twenty);
		}
		if (currency.getTen() >= ten) {
			currency.setTen(currency.getTen() - ten);
		}
		if (currency.getFive() >= five) {
			currency.setFive(currency.getFive() - five);
		}
		if (currency.getOne() >= one) {
			currency.setOne(currency.getOne() - one);
		}
		
		currencyServiceImpl.updateCurrency(currency);
		
		StringBuilder sb = new StringBuilder();
		sb.append("Dispensing:\n");
		sb.append("$1," + one + "\n");
		sb.append("$5," + five + "\n");
		sb.append("$10," + ten + "\n");
		sb.append("$20," + twenty + "\n");
		sb.append("$100," + hundred);
		return sb.toString();
	}
}
