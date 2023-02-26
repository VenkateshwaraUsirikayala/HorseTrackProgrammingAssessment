package com.encora.assessement.horsetracking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Bet {

	@Id
	@GeneratedValue
	private Integer id;
	
    private Integer horse;
    private Integer bet;

}
