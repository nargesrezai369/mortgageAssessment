package com.example.mortgageassessment.config;

import com.example.mortgageassessment.domain.InterestRate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Create mortgage rate in memory
 *
 * @author : Narges Rezaei
 **/
@Configuration
public class InMemoryDataConfiguration {

    @Bean
    public List<InterestRate> interestRates() {

        LocalDateTime now = LocalDateTime.now();

        InterestRate rate1 = new InterestRate(5, new BigDecimal("1.03"), now);
        InterestRate rate2 = new InterestRate(10, new BigDecimal("1.14"), now);
        InterestRate rate3 = new InterestRate(20, new BigDecimal("2.5"), now);
        InterestRate rate4 = new InterestRate(40, new BigDecimal("4.2"), now);

        return List.of(rate1, rate2, rate3, rate4);

    }

}
