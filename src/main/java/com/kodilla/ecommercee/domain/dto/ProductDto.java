package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {

    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Long groupId;
    private List<ProductsInCartDto> productsInCartDtos;
    private List<OrderItemDto> orderItemsDtos;

}

