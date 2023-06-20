package com.example.mortgageassessment.models;

import lombok.*;

import java.math.BigDecimal;


/**
 * Mortgage check request dto
 *
 * @author : Narges Rezaei
 **/
@Setter
@Getter
@Builder
public class MortgageCheckRequestDto {

    private BigDecimal income;

    private int maturityPeriod;

    private BigDecimal loanValue;

    private BigDecimal homeValue;

}
