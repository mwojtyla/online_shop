package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
public class OrderItemDto {
    private Long orderItemId;
    private String orderStatus;
    private BigDecimal price;
    private Long productsId;
    private Long orderId;
    private Long quantity;
}
