package com.zara.prices.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zara.prices.DemoApplication;
import com.zara.prices.dto.PriceGetDto;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc

class PriceResourceIT {

    @Autowired
    private MockMvc restMockMvc;

    @Autowired
    ObjectMapper objectMapper;

    protected static Stream<Arguments> provideParameters() {
         return Stream.of(
                Arguments.of("2020-06-14 10:00:00", new PriceGetDto(1L,new Date(), new Date(),1,35455,0,35.5,"EUR")),
                Arguments.of("2020-06-14 16:00:00", new PriceGetDto(1L,new Date(), new Date(),2,35455,1,25.45,"EUR")),
                Arguments.of("2020-06-14 21:00:00", new PriceGetDto(1L,new Date(), new Date(),1,35455,0,35.5,"EUR")),
                 Arguments.of("2020-06-15 10:00:00",new PriceGetDto(1L,new Date(), new Date(),3,35455,1,30.5,"EUR")),
                 Arguments.of("2020-06-16 21:00:00", new PriceGetDto(1L,new Date(), new Date(),4,35455,1,38.95,"EUR"))

        );
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    void shouldObtainPrice(String date, PriceGetDto resultPriceDto) throws Exception {
        MvcResult resultRequest = restMockMvc.perform(get("/api/price?")
                .param("brandId","1")
                .param("productId", "35455")
                .param("date", date))
                .andExpect(status().isOk()).andReturn();

        PriceGetDto result = objectMapper.readValue(resultRequest.getResponse().getContentAsString(), PriceGetDto.class);
        assertThat(result).usingRecursiveComparison().ignoringFields("startDate","endDate").isEqualTo(resultPriceDto);
    }

}
