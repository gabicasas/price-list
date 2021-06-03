package com.zara.prices.web.rest;

import com.zara.prices.dto.PriceGetDto;
import com.zara.prices.service.PriceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/api")
public class PriceResource {

    private final PriceService priceService;

    public PriceResource(PriceService priceService) {
        this.priceService = priceService;
    }

    /**
     * Gets price.
     *
     * @param date      the date
     * @param productId the product id
     * @param brandId   the brand id
     * @return the price
     */
    @ApiOperation(value = "Get prices for product in brand in one moment", response = PriceGetDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message="Price returned", response = PriceGetDto.class),
            @ApiResponse(code=404, message="Price not found")
    })
    @GetMapping(path = "/price")
    public ResponseEntity<PriceGetDto> getPrice(@RequestParam(value = "date") LocalDateTime date,
                                                @RequestParam(value = "productId") Integer productId,
                                                @RequestParam(value = "brandId") Long brandId) {

        return priceService.getPrice(brandId, productId, java.util.Date
                .from(date.atZone(ZoneId.systemDefault())
                        .toInstant()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
