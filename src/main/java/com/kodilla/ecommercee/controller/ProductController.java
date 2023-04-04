package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/shop/products")
@RequiredArgsConstructor
public class ProductController {

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return new ArrayList<>();
    }

    @GetMapping("/{productId}")
    public ProductDto getProductById(@PathVariable Long productId) {
        return new ProductDto(1L, "Coat", "Black and White", new BigDecimal(1500), 1L, 123L, 4444L);
    }

    @PostMapping
    public void createProduct(@RequestBody ProductDto productDto) {

    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return new ProductDto(1L, "Coat", "Black and White", new BigDecimal(1300), 1L, 123L, 4444L);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {

    }
}