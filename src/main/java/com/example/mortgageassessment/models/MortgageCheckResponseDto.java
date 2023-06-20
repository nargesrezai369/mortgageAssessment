package com.example.mortgageassessment.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Mortgage check response dto
 *
 * @author : Narges Rezaei
 **/
@Setter
@Getter
@Builder
public class MortgageCheckResponseDto {

    private boolean feasible;

    private BigDecimal monthlyCosts;

}
