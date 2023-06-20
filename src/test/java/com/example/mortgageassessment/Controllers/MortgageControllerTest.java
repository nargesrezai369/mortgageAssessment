package com.example.mortgageassessment.Controllers;

import com.example.mortgageassessment.MortgageAssessmentApplication;
import com.example.mortgageassessment.models.InterestRateDto;
import com.example.mortgageassessment.models.MortgageCheckResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Description;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : Narges Rezaei
 **/
@SpringBootTest(classes = MortgageAssessmentApplication.class)
@AutoConfigureMockMvc
public class MortgageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    @Description("Get interest rate list.")
    public void getInterestRateTest() throws Exception {

        //************************
        //          WHEN
        //************************
        mockMvc.perform(get("/api/interest-rates"))
                .andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(Matchers.is(4)))
                .andExpect(jsonPath("$[0].maturityPeriod").value(5))
                .andExpect(jsonPath("$[1].maturityPeriod").value(10))
                .andExpect(jsonPath("$[2].maturityPeriod").value(20))
                .andExpect(jsonPath("$[3].maturityPeriod").value(40))
                .andExpect(jsonPath("$[0].interestRate").value(1.03))
                .andExpect(jsonPath("$[1].interestRate").value(1.14))
                .andExpect(jsonPath("$[2].interestRate").value(2.5))
                .andExpect(jsonPath("$[3].interestRate").value(4.2))
                .andReturn();

    }

    @Test
    @Order(2)
    @Description("check Mortgage Invalid InterestRate Test.")
    public void checkMortgageInvalidInterestRateTest() throws Exception {

        //************************
        //          Given
        //************************
        String requestBody = "{ \"homeValue\": 40, \"income\": 20, \"loanValue\": 20, \"maturityPeriod\": 33}";

        //************************
        //          WHEN
        //************************
        mockMvc.perform(post("/api/mortgage-check")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();

    }

    @Test
    @Order(3)
    @Description("a mortgage is exceed 4 times the income Test.")
    public void checkMortgageInvalidIncomeTest() throws Exception {

        //************************
        //          Given
        //************************
        String requestBody = "{ \"homeValue\": 400, \"income\": 20, \"loanValue\": 90, \"maturityPeriod\": 5}";

        //************************
        //          WHEN
        //************************
        MvcResult result = mockMvc.perform(post("/api/mortgage-check")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //************************
        //          THEN
        //************************
        String response = result.getResponse().getContentAsString();

        assertThat(response).isNotBlank();

        MortgageCheckResponseDto dto = objectMapper.readValue(response, MortgageCheckResponseDto.class);

        assertThat(dto.isFeasible()).isFalse();

    }

    @Test
    @Order(4)
    @Description("a mortgage is exceed the home value Test.")
    public void checkMortgageInvalidHomeValueTest() throws Exception {

        //************************
        //          Given
        //************************
        String requestBody = "{ \"homeValue\": 40, \"income\": 20, \"loanValue\": 70, \"maturityPeriod\": 5}";

        //************************
        //          WHEN
        //************************
        MvcResult result = mockMvc.perform(post("/api/mortgage-check")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //************************
        //          THEN
        //************************
        String response = result.getResponse().getContentAsString();

        assertThat(response).isNotBlank();

        MortgageCheckResponseDto dto = objectMapper.readValue(response, MortgageCheckResponseDto.class);

        assertThat(dto.isFeasible()).isFalse();

    }

    @Test
    @Order(5)
    @Description("Successful Test.")
    public void checkMortgageOkTest() throws Exception {

        //************************
        //          Given
        //************************
        String requestBody = "{ \"homeValue\": 400, \"income\": 20, \"loanValue\": 70, \"maturityPeriod\": 5}";

        //************************
        //          WHEN
        //************************
        MvcResult result = mockMvc.perform(post("/api/mortgage-check")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //************************
        //          THEN
        //************************
        String response = result.getResponse().getContentAsString();

        assertThat(response).isNotBlank();

        MortgageCheckResponseDto dto = objectMapper.readValue(response, MortgageCheckResponseDto.class);

        assertThat(dto.isFeasible()).isTrue();

    }

}
