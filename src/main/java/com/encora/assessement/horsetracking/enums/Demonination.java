package com.encora.assessement.horsetracking.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Demonination {

    HUNDRED(100),
    TWENTY(20),
    TEN(10),
    FIVE(5),
    ONE(1);

    private int value;

}



