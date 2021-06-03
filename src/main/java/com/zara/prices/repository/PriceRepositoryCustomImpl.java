package com.zara.prices.repository;

import com.zara.prices.domain.Price;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.Optional;

@Repository
public class PriceRepositoryCustomImpl implements PriceRepositoryCustom {

    private final EntityManager entityManager;

    public PriceRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Price> findPriceByIdAndBrandBetweenDates(Long brandId, Integer productId, Date applyDate) {
        var jql = "SELECT p FROM Price p WHERE " +
                "p.brand.id = :brandId and " +
                "p.productId = :productId and " +
                "p.startDate <= :date and " +
                "p.endDate>= :date " +
                "ORDER BY p.priority DESC";

        TypedQuery<Price> query = entityManager.createQuery(jql, Price.class);
        query.setParameter("brandId", brandId)
                .setParameter("productId", productId)
                .setParameter("date", applyDate)
                .setMaxResults(1);

        var prices = query.getResultList();
        if (CollectionUtils.isEmpty(prices))
            return Optional.empty();

        return Optional.of(prices.get(0));


    }
}
