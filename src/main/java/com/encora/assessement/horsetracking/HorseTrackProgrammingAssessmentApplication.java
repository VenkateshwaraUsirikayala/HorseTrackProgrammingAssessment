package com.encora.assessement.horsetracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.encora.assessement.horsetracking.model.Bet;
import com.encora.assessement.horsetracking.model.Currency;
import com.encora.assessement.horsetracking.model.Horse;
import com.encora.assessement.horsetracking.repository.BetRepository;
import com.encora.assessement.horsetracking.repository.CurrencyRepository;
import com.encora.assessement.horsetracking.repository.HorseRepository;
import com.encora.assessement.horsetracking.service.display.DisplayService;
import com.encora.assessement.horsetracking.service.impl.ExecuteProcessServiceImpl;

@SpringBootApplication
public class HorseTrackProgrammingAssessmentApplication implements CommandLineRunner {

	@Autowired
	DisplayService displayService;

	@Autowired
	ExecuteProcessServiceImpl processServiceImpl;

	@Autowired
	HorseRepository horseRepository;
	
	@Autowired
	CurrencyRepository currencyRepository;
	
	@Autowired
	BetRepository betRepository;

	public static void main(String[] args) {
		SpringApplication.run(HorseTrackProgrammingAssessmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<Horse> horses = new ArrayList<>();
		horses.add(Horse.builder().name("That Darn Gray Cat").odds(5).won(true).build());
		horses.add(Horse.builder().name("Fort Utopia").odds(10).won(false).build());
		horses.add(Horse.builder().name("Count Sheep").odds(9).won(false).build());
		horses.add(Horse.builder().name("Ms Traitour").odds(4).won(false).build());
		horses.add(Horse.builder().name("Real Princess").odds(3).won(false).build());
		horses.add(Horse.builder().name("Pa Kettle").odds(5).won(false).build());
		horses.add(Horse.builder().name("Gin Stinger").odds(5).won(false).build());

		horseRepository.saveAll(horses);
		
		Currency currency = new Currency(1, 10, 10, 10, 10, 10);
		currencyRepository.save(currency);
		
		Bet bet = new Bet(1,0,0);
		betRepository.save(bet);
		

		displayService.displayCurrencyInventoryAndListOfHorses();

		try (Scanner console = new Scanner(System.in)) {
			do {
				processServiceImpl.processInput(console.nextLine());
			} while (true);
		}

	}

}
