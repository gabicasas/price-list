package com.zara.prices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceGetDto {

    private Long brandId;

    private Date startDate;

    private Date endDate;

    private Integer priceListId;

    private Integer productId;

    private Integer priority;

    private Double price;

    private String currency;
}
