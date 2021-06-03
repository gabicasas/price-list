package com.zara.prices.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BRANDS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

    @Id
    private Long id;

    @NotNull
    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

}
