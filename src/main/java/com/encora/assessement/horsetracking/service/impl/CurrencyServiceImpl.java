package com.encora.assessement.horsetracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encora.assessement.horsetracking.constants.AppConstants;
import com.encora.assessement.horsetracking.model.Currency;
import com.encora.assessement.horsetracking.repository.CurrencyRepository;
import com.encora.assessement.horsetracking.service.CurrencyService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	CurrencyRepository currencyRepository;

	@Override
	public Currency getCurrency() {
		Currency currency = currencyRepository.findAll().iterator().next();
		return currency;
	}

	@Override
	public void reloadCurrencyInventory() {

		Currency currency = getCurrency();
		currency.setOne(AppConstants.DEFAULT_DENOMINATION);
		currency.setFive(AppConstants.DEFAULT_DENOMINATION);
		currency.setTen(AppConstants.DEFAULT_DENOMINATION);
		currency.setTwenty(AppConstants.DEFAULT_DENOMINATION);
		currency.setHundred(AppConstants.DEFAULT_DENOMINATION);
		currencyRepository.save(currency);

	}

	@Override
	public int getTotalCashOnHand() {

		Currency currency = getCurrency();
		int one = currency.getOne();
		int five = currency.getFive();
		int ten = currency.getTen();
		int twenty = currency.getTwenty();
		int hundred = currency.getHundred();

		return (one * 1) + (five * 5) + (ten * 10) + (twenty * 20) + (hundred * 100);
	}

	@Override
	public boolean updateCurrency(Currency currency) {
		return currencyRepository.save(currency)!=null;
	}
	
	@Override
	public void displayCurrencyInventory() {
		Currency currency = getCurrency();
		StringBuilder sb = new StringBuilder();
		sb.append("Inventory:\n");
		sb.append("$1," + currency.getOne() + "\n");
		sb.append("$5," + currency.getFive() + "\n");
		sb.append("$10 " + currency.getTen() + "\n");
		sb.append("$20," + currency.getTwenty() + "\n");
		sb.append("$100," + currency.getHundred() + "\n");
		System.out.println(sb.toString().trim());
	}

	

}