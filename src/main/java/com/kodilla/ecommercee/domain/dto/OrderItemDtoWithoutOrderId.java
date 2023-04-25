package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
public class OrderItemDtoWithoutOrderId {
    private Long orderItemId;
    private BigDecimal price;
    private Long productsId;
    private int productQuantity;
}