package com.zara.prices.repository;

import com.zara.prices.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    /**
     * Find price by id and brand between dates .
     *
     * @param brandId   the brandId
     * @param brandId   the brand id
     * @param applyDate the apply date
     * @return the optional
     */
    @Query("SELECT p FROM Price p WHERE " +
            "p.brand.id = ?1 and " +
            "p.productId = ?2 and " +
            "p.startDate <= ?3 and " +
            "p.endDate>= ?3 " +
            "ORDER BY p.priority DESC")
    List<Price> findPricesByIdAndBrandBetweenDates(Long brandId, Integer productId, Date applyDate);

    default Optional<Price> findPriceByIdAndBrandBetweenDates(Long brandId, Integer productId, Date applyDate) {
        var prices = findPricesByIdAndBrandBetweenDates(brandId, productId, applyDate);
        if (CollectionUtils.isEmpty(prices))
            return Optional.empty();

        return Optional.of(prices.get(0));
    }
}
