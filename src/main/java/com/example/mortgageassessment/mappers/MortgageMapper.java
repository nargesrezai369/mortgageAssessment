package com.example.mortgageassessment.mappers;

import com.example.mortgageassessment.domain.InterestRate;
import com.example.mortgageassessment.models.InterestRateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Map models to each other
 *
 * @author : Narges Rezaei
 **/
@Mapper
public interface MortgageMapper {

    MortgageMapper INSTANCE = Mappers.getMapper(MortgageMapper.class);

    InterestRateDto map(InterestRate model);

    List<InterestRateDto> map(List<InterestRate> list);

}
