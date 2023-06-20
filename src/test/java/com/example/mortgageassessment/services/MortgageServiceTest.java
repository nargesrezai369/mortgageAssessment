package com.example.mortgageassessment.services;

import com.example.mortgageassessment.MortgageAssessmentApplication;
import com.example.mortgageassessment.exceptions.InterestRateNotFoundException;
import com.example.mortgageassessment.models.InterestRateDto;
import com.example.mortgageassessment.models.MortgageCheckRequestDto;
import com.example.mortgageassessment.models.MortgageCheckResponseDto;
import jdk.jfr.Description;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author : Narges Rezaei
 **/
@SpringBootTest(classes = MortgageAssessmentApplication.class)
public class MortgageServiceTest {

    @Autowired
    private MortgageService mortgageService;

    @Order(1)
    @Test
    @Description("Get interest rate list.")
    public void getInterestRateTest() {

        //************************
        //          WHEN
        //************************
        List<InterestRateDto> result = mortgageService.interestRates();

        //************************
        //          THEN
        //************************
        assertThat(result.size()).isEqualTo(4);

        assertThat(result.get(0).getMaturityPeriod()).isEqualTo(5);
        assertThat(result.get(0).getInterestRate().doubleValue()).isEqualTo(1.03);
        assertThat(result.get(1).getMaturityPeriod()).isEqualTo(10);
        assertThat(result.get(1).getInterestRate().doubleValue()).isEqualTo(1.14);
        assertThat(result.get(2).getMaturityPeriod()).isEqualTo(20);
        assertThat(result.get(2).getInterestRate().doubleValue()).isEqualTo(2.5);
        assertThat(result.get(3).getMaturityPeriod()).isEqualTo(40);
        assertThat(result.get(3).getInterestRate().doubleValue()).isEqualTo(4.2);

    }

    @Test
    @Order(2)
    @Description("check Mortgage Invalid InterestRate Test.")
    public void checkMortgageInvalidInterestRateTest() {

        //************************
        //          Given
        //************************
        MortgageCheckRequestDto dto = MortgageCheckRequestDto.builder()
                .homeValue(new BigDecimal(40))
                .income(new BigDecimal(20))
                .loanValue(new BigDecimal(20))
                .maturityPeriod(33)
                .build();

        //************************
        //          WHEN
        //************************
        assertThrows(InterestRateNotFoundException.class, () -> {
            mortgageService.mortgageCheck(dto);
        });

    }

    @Test
    @Order(3)
    @Description("a mortgage is exceed 4 times the income Test.")
    public void checkMortgageInvalidIncomeTest() throws Exception {

        //************************
        //          Given
        //************************
        MortgageCheckRequestDto dto = MortgageCheckRequestDto.builder()
                .homeValue(new BigDecimal(40))
                .income(new BigDecimal(20))
                .loanValue(new BigDecimal(90))
                .maturityPeriod(5)
                .build();

        //************************
        //          WHEN
        //************************
        MortgageCheckResponseDto result = mortgageService.mortgageCheck(dto);

        //************************
        //          THEN
        //************************

        assertThat(result.isFeasible()).isFalse();

    }

    @Test
    @Order(4)
    @Description("a mortgage is exceed the home value Test.")
    public void checkMortgageInvalidHomeValueTest() throws Exception {

        //************************
        //          Given
        //************************
        MortgageCheckRequestDto dto = MortgageCheckRequestDto.builder()
                .homeValue(new BigDecimal(40))
                .income(new BigDecimal(20))
                .loanValue(new BigDecimal(70))
                .maturityPeriod(5)
                .build();

        //************************
        //          WHEN
        //************************
        MortgageCheckResponseDto result = mortgageService.mortgageCheck(dto);

        //************************
        //          THEN
        //************************

        assertThat(result.isFeasible()).isFalse();

    }

    @Test
    @Order(5)
    @Description("Successful Test.")
    public void checkMortgageOkTest() throws Exception {

        //************************
        //          Given
        //************************
        String requestBody = "{ \"homeValue\": 400, \"income\": 20, \"loanValue\": 70, \"maturityPeriod\": 5}";
        MortgageCheckRequestDto dto = MortgageCheckRequestDto.builder()
                .homeValue(new BigDecimal(400))
                .income(new BigDecimal(20))
                .loanValue(new BigDecimal(70))
                .maturityPeriod(5)
                .build();

        //************************
        //          WHEN
        //************************
        MortgageCheckResponseDto result = mortgageService.mortgageCheck(dto);

        //************************
        //          THEN
        //************************

        assertThat(result.isFeasible()).isTrue();

    }
}
