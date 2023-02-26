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
			displayService.displayErrorMessage(AppConstants.INSUFFICIENT_FUNDS_ERROR + payout);
		}
		
		return calculateDenominationAndDispense(currency, payout);
	}

	private String calculateDenominationAndDispense(Currency currency, Integer payout) {
		int hundred = 0;
		int twenty = 0;
		int ten = 0;
		int five = 0;
		int one = 0;
		if (payout >= 100) {
			hundred = payout / 100;
			payout = payout % 100;
		}
		if (payout >= 20) {
			twenty = payout / 20;
			payout = payout % 20;
		}
		if (payout >= 10) {
			ten = payout / 10;
			payout = payout % 10;
		}
		if (payout >= 5) {
			five = payout / 5;
			payout = payout % 5;
		}
		if (payout >= 1) {
			one = payout / 1;
			payout = payout % 1;
		}

		boolean hasSufficientFunds = hasSufficientFunds(currency, hundred, twenty, ten, five, one);
		if(!hasSufficientFunds) {
			return StringUtils.EMPTY;
		}

		StringBuilder sb = new StringBuilder();
		sb.append("Dispensing:\n");
		sb.append("$1," + one + "\n");
		sb.append("$5," + five + "\n");
		sb.append("$10," + ten + "\n");
		sb.append("$20," + twenty + "\n");
		sb.append("$100," + hundred);
		return sb.toString();
	}

	private boolean hasSufficientFunds(Currency currency, int hundred, int twenty, int ten, int five, int one) {
		
		int oldHundred = currency.getHundred();
        int oldTwenty = currency.getTwenty();
        int oldTen = currency.getTen();
        int oldFive = currency.getFive();
        int oldOne = currency.getOne();

		boolean hasSufficientFunds = true;
		if (currency.getHundred() >= hundred) {
			currency.setHundred(currency.getHundred() - hundred);
		}else {
			hasSufficientFunds=false;
		}
		if (currency.getTwenty() >= twenty) {
			currency.setTwenty(currency.getTwenty() - twenty);
		}else {
			hasSufficientFunds=false;
		}
		if (currency.getTen() >= ten) {
			currency.setTen(currency.getTen() - ten);
		}else {
			hasSufficientFunds=false;
		}
		if (currency.getFive() >= five) {
			currency.setFive(currency.getFive() - five);
		}else {
			hasSufficientFunds=false;
		}
		if (currency.getOne() >= one) {
			currency.setOne(currency.getOne() - one);
		}else {
			hasSufficientFunds=false;
		}
		
		if (!hasSufficientFunds) {
			currency.setHundred(oldHundred);
			currency.setTwenty(oldTwenty);
			currency.setTen(oldTen);
			currency.setFive(oldFive);
			currency.setOne(oldOne);
		}

		currencyServiceImpl.updateCurrency(currency);
		return hasSufficientFunds;
	}
}
