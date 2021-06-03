package com.zara.prices.service;

import com.zara.prices.domain.Price;
import com.zara.prices.dto.PriceGetDto;
import com.zara.prices.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    PriceRepository repository;

    @InjectMocks
    PriceService service;

    @Test
    void shouldReturnOptionalPriceWhenRepositoryReturns(){
        when(repository.findPriceByIdAndBrandBetweenDates(any(),any(),any())).thenReturn(Optional.of(new Price()));
        Optional<PriceGetDto> result= service.getPrice(1L,1,new Date());
        assertTrue(result.isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalPriceWhenRepositoryReturnsEmpty(){
        when(repository.findPriceByIdAndBrandBetweenDates(any(),any(),any())).thenReturn(Optional.empty());
        Optional<PriceGetDto> result= service.getPrice(1L,1,new Date());
        assertTrue(result.isEmpty());
    }

}