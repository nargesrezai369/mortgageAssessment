package com.example.mortgageassessment.services;

import com.example.mortgageassessment.domain.InterestRate;
import com.example.mortgageassessment.exceptions.InterestRateNotFoundException;
import com.example.mortgageassessment.mappers.MortgageMapper;
import com.example.mortgageassessment.models.InterestRateDto;
import com.example.mortgageassessment.models.MortgageCheckRequestDto;
import com.example.mortgageassessment.models.MortgageCheckResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Mortgage services.
 *
 * @author : Narges Rezaei
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class MortgageServiceSimple implements MortgageService {

    private final List<InterestRate> interestRates;

    private final MortgageMapper mortgageMapper;

    /**
     * get interest rates
     *
     * @return List<InterestRateDto>
     */
    @Override
    public List<InterestRateDto> interestRates() {

        log.info("interest rate api is called.");

        return mortgageMapper.map(interestRates);

    }

    /**
     * check mortgage
     *
     * @param dto
     * @return MortgageCheckResponseDto
     */
    @Override
    public MortgageCheckResponseDto mortgageCheck(MortgageCheckRequestDto dto) {

        log.info("mortgage check api is called.");

        boolean feasible = getFeasibleLoan(dto);

        BigDecimal monthlyCosts = feasible
                ? calculateMonthlyCosts(dto.getLoanValue(), dto.getMaturityPeriod())
                : (BigDecimal.ZERO);

        return MortgageCheckResponseDto.builder()
                .feasible(feasible)
                .monthlyCosts(monthlyCosts)
                .build();

    }

    /**
     * check feasible for loan,
     * a mortgage should not exceed 4 times the income and
     * a mortgage should not exceed the home value.
     *
     * @param request
     * @return boolean
     */
    private boolean getFeasibleLoan(MortgageCheckRequestDto request) {

        BigDecimal maxMortgageBigDecimal = request.getIncome().multiply(BigDecimal.valueOf(4));
        BigDecimal loanValue = request.getLoanValue();
        BigDecimal homeValue = request.getHomeValue();

        boolean feasible = loanValue.compareTo(maxMortgageBigDecimal) <= 0 && loanValue.compareTo(homeValue) <= 0;

        return feasible;

    }

    /**
     * calculate monthly costs,
     *
     * @param loanValue
     * @param maturityPeriod
     * @return BigDecimal
     */
    private BigDecimal calculateMonthlyCosts(BigDecimal loanValue, int maturityPeriod) {

        BigDecimal interestRate = getCurrentInterestRate(maturityPeriod);

        //implement your algorithm for calculate monthly cost base on rate
        BigDecimal monthlyCosts = BigDecimal.ZERO;

        return monthlyCosts;

    }

    /**
     * check if the rate is in in-memory model.
     *
     * @param maturityPeriod
     * @return BigDecimal
     */
    private BigDecimal getCurrentInterestRate(int maturityPeriod) {

        return interestRates.stream()
                .filter(rate -> rate.getMaturityPeriod() == maturityPeriod)
                .map(InterestRate::getInterestRate)
                .findFirst()
                .orElseThrow(() -> new InterestRateNotFoundException
                        ("No interest rate found for maturity period: " + maturityPeriod));

    }

}
