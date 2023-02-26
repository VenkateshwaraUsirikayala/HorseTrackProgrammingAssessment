package com.encora.assessement.horsetracking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Currency {
	
	@Id
	@GeneratedValue
	private int id;

	private int one;
	private int five;
	private int ten;
	private int twenty;
	private int hundred;

}
