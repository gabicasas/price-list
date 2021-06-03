package com.zara.prices.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRICES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BRAND_ID")
    private Brand brand;

    @Column(name = "START_DATE", nullable=false)
    private Date startDate;

    @Column(name = "END_DATE", nullable=false)
    private Date endDate;

    @Column(name = "PRICE_LIST", nullable=false)
    private Integer priceListId;

    @Column(name = "PRODUCT_ID", nullable=false)
    private Integer productId;

    @Column(name = "PRIORITY", nullable=false)
    private Integer priority;

    @Column(name = "PRICE", nullable=false)
    private Double price;

    @Column(name = "CURR", nullable=false)
    private String currency;

}
