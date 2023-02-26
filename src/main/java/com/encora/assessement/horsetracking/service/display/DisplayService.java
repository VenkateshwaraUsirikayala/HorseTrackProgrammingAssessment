package com.encora.assessement.horsetracking.service.display;

public interface DisplayService extends InventoryDisplayService, HorseDisplayService, MessageDisplayService{

	public default void displayCurrencyInventoryAndListOfHorses() {
		displayInventory();
		displayHorses();
	}
}
