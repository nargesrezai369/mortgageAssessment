package com.example.mortgageassessment.controllers;

import com.example.mortgageassessment.models.InterestRateDto;
import com.example.mortgageassessment.models.MortgageCheckRequestDto;
import com.example.mortgageassessment.models.MortgageCheckResponseDto;
import com.example.mortgageassessment.services.MortgageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Mortgage APIs
 *
 * @author : Narges Rezaei
 **/
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MortgageController {

    private final MortgageService mortgageService;

    /**
     * Get interest mortgage rates List
     *
     * @return List<InterestRateDto>
     */
    @GetMapping("/interest-rates")
    public ResponseEntity<List<InterestRateDto>> getInterestRate() {

        log.info(" /interest-rates api is called.");

        List<InterestRateDto> response = mortgageService.interestRates();

        return response.stream()
                .findAny()
                .map(data -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.notFound().build());

    }

    /**
     * check mortgage
     *
     * @param dto
     * @return MortgageCheckResponseDto
     */
    @PostMapping("/mortgage-check")
    public ResponseEntity<MortgageCheckResponseDto> checkMortgage(@RequestBody MortgageCheckRequestDto dto) {

        log.info(" /mortgage-check api is called.");

        return ResponseEntity.status(HttpStatus.OK).body(mortgageService.mortgageCheck(dto));

    }

}
