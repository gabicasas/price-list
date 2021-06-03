package com.zara.prices.repository;

import com.zara.prices.domain.Brand;
import com.zara.prices.domain.Price;
import com.zara.prices.dto.PriceGetDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PriceRepositoryTest {

    @Autowired
    PriceRepository priceRepository;

    protected static Stream<Arguments> provideParameters() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Brand Zara = new Brand(1L, "ZARA");
        try {
            return Stream.of(Arguments.of(1L,35455, sdf.parse("2020-06-14 10:00:00"), new Price(1L,
                    Zara,
                            sdf.parse("2020-06-14 00:00:00"),
                            sdf.parse("2020-12-31 23:59:59"),
                    1,
                    35455,
                    0,
                    35.50,
                    "EUR")),
                    Arguments.of(1L,35455, sdf.parse("2020-06-14 16:00:00"),new Price(2L,
                            Zara,
                            sdf.parse("2020-06-14 00:00:00"),
                            sdf.parse("2020-12-31 23:59:59"),

                            2,
                            35455,
                            1,
                            25.45,
                            "EUR")),
                    Arguments.of(1L,35455, sdf.parse("2020-06-14 21:00:00"),new Price(1L,
                            Zara,
                            new Date(),
                            new Date(),
                            1,
                            35455,
                            0,
                            35.50,
                            "EUR")),
                    Arguments.of(1L,35455, sdf.parse("2020-06-15 10:00:00"),new Price(3L,
                            Zara,
                            new Date(),
                            new Date(),
                            3,
                            35455,
                            1,
                            30.50,
                            "EUR")),
                    Arguments.of(1L,35455, sdf.parse("2020-06-16 21:00:00"),new Price(4L,
                            Zara,
                            new Date(),
                            new Date(),
                            4,
                            35455,
                            1,
                            38.95,
                            "EUR"))
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;


    }

    @ParameterizedTest
    @MethodSource("provideParameters")

    void shouldReturnPrices(Long brandId, Integer productId, Date date, Price expectedResult) {
        Optional<Price> result = priceRepository.findPriceByIdAndBrandBetweenDates(brandId,productId,date);
        assertThat(result.get()).usingRecursiveComparison().ignoringFields("brand","startDate","endDate").isEqualTo(expectedResult);

    }

}