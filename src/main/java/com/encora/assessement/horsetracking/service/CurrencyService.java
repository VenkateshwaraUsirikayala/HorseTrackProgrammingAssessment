package com.encora.assessement.horsetracking.service;

import com.encora.assessement.horsetracking.model.Currency;

public interface CurrencyService {

	Currency getCurrency();

	void reloadCurrencyInventory();

	int getTotalCashOnHand();

	boolean updateCurrency(Currency currency);

	void displayCurrencyInventory();

}