package com.zara.prices.mapper;

import com.zara.prices.domain.Price;
import com.zara.prices.dto.PriceGetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PriceMapper {

    @Mapping( source = "brand.id", target = "brandId")
    PriceGetDto map(Price price);
}
