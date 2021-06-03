package com.zara.prices.repository;

import com.zara.prices.domain.Price;

import java.util.Date;
import java.util.Optional;

/**
 * The interface Price repository custom.
 */
public interface PriceRepositoryCustom {

    /**
     * Find price by id and brand between dates.
     *
     * @param brandId   the brand id
     * @param productId the product id
     * @param applyDate the apply date
     * @return the optional
     */
    Optional<Price> findPriceByIdAndBrandBetweenDates(Long brandId, Integer productId, Date applyDate);
}
