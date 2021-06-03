package com.zara.prices.mapper;

import com.zara.prices.domain.Brand;
import com.zara.prices.domain.Price;
import com.zara.prices.dto.PriceGetDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PriceMapperTest {

    PriceMapper mapper = Mappers.getMapper(PriceMapper.class);

    @Test
    void shouldBeSimilar() {
        Price price = new Price(1L,
                new Brand(1L, "brandName"),
                new Date(),
                new Date(),
                2,
                3,
                5,
                4.0,
                "CURRENCY");
        PriceGetDto priceGetDto = mapper.map(price);
        assertThat(price).usingRecursiveComparison().ignoringFields("brand", "brandId", "id").isEqualTo(priceGetDto);
        assertThat(price.getBrand().getId()).isEqualTo(priceGetDto.getBrandId());
    }
}