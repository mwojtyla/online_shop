package com.kodilla.ecommercee.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class OrderDto {
    private Long orderId;
    private LocalDate dateOfOrder;
    private String orderStatus;
    private Long userId;
    private Long ordersItemsId;
}
