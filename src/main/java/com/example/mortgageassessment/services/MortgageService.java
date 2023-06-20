package com.example.mortgageassessment.services;


import com.example.mortgageassessment.models.InterestRateDto;
import com.example.mortgageassessment.models.MortgageCheckRequestDto;
import com.example.mortgageassessment.models.MortgageCheckResponseDto;

import java.util.List;

/**
 * Mortgage services .
 *
 * @author : Narges Rezaei
 **/
public interface MortgageService {

    List<InterestRateDto> interestRates();

    MortgageCheckResponseDto mortgageCheck(MortgageCheckRequestDto dto);

}
