package com.zara.prices.repository;

import com.zara.prices.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long>, PriceRepositoryCustom { }
