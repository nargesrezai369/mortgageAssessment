package com.example.mortgageassessment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Interest Rate model class
 *
 * @author : Narges Rezaei
 **/
@Setter
@Getter
@AllArgsConstructor
public class InterestRate {

    private int maturityPeriod;

    private BigDecimal interestRate;

    private LocalDateTime lastUpdate;

}
