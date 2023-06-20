package com.example.mortgageassessment.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Interest rate get dto
 *
 * @author : Narges Rezaei
 **/
@Setter
@Getter
public class InterestRateDto {

    private int maturityPeriod;

    private BigDecimal interestRate;

    private String lastUpdate;

}
