package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductsInCart;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.domain.dto.ProductsInCartDto;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.exception.ProductsInCartNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.mapper.ProductsInCartMapper;
import com.kodilla.ecommercee.service.GroupDbService;
import com.kodilla.ecommercee.service.ProductDbService;
import com.kodilla.ecommercee.service.ProductsInCartDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/shop/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    private final ProductDbService productDBService;
    private final ProductMapper productMapper;
    private final GroupDbService groupDBService;
    private final ProductsInCartMapper productsInCartMapper;
    private final ProductsInCartDbService productsInCartDBService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = productDBService.getAllProducts();
        List<ProductDto> productDtos = productMapper.mapToProductDtoList(products);
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/productsInCart")
    public ResponseEntity<List<ProductsInCartDto>> getProductsInCart() {
        List<ProductsInCart> productsInCartList = productsInCartDBService.getAllProductsInCart();
        List<ProductsInCartDto> productsInCartDtos = productsInCartMapper.mapToProductsInCartDtoList(productsInCartList);
        return ResponseEntity.ok(productsInCartDtos);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) throws ProductNotFoundException {
        Product product = productDBService.getProductById(productId);
        ProductDto productDto = productMapper.mapToProductDto(product);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/productsInCart/{productsInCartId}")
    public ResponseEntity<ProductsInCartDto> getProductInCartById(@PathVariable Long productsInCartId) throws ProductsInCartNotFoundException {
        ProductsInCart productsInCart = productsInCartDBService.getProductsInCartById(productsInCartId);
        ProductsInCartDto productsIncartDto = productsInCartMapper.mapToProductsInCartDto(productsInCart);
        return ResponseEntity.ok(productsIncartDto);
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        groupDBService.saveGroup(product.getGroup());
        productDBService.saveProduct(product);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/productsInCart")
    public ResponseEntity<Void> createProductsInCart(@RequestBody ProductsInCartDto productsInCartDto) throws ProductNotFoundException {
        ProductsInCart productsInCart = productsInCartMapper.mapToProductsInCart(productsInCartDto);
        productsInCartDBService.saveProductsInCart(productsInCart);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto) throws ProductNotFoundException {
        Product productToUpdate = productDBService.getProductById(productId);
        productToUpdate.setName(productDto.getName());
        productToUpdate.setDescription(productDto.getDescription());
        productToUpdate.setPrice(productDto.getPrice());
        Product updatedProduct = productDBService.updateProduct(productToUpdate);
        ProductDto updatedProductDto = productMapper.mapToProductDto(updatedProduct);
        return ResponseEntity.ok(updatedProductDto);
    }

    @PutMapping("/productsInCart/{productsInCartId}")
    public ResponseEntity<ProductsInCartDto> updateProductsInCart(@PathVariable Long productsInCartId, @RequestBody ProductsInCartDto productsInCartDto) throws ProductsInCartNotFoundException {
        ProductsInCart productsInCartToUpdate = productsInCartDBService.getProductsInCartById(productsInCartId);
        productsInCartToUpdate.setProductQuantity(productsInCartDto.getProductQuantity());
        ProductsInCart updatedProductsInCart = productsInCartDBService.updateProductsInCart(productsInCartToUpdate);
        ProductsInCartDto updatedProductDto = productsInCartMapper.mapToProductsInCartDto(updatedProductsInCart);
        return ResponseEntity.ok(updatedProductDto);
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productDBService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/productsInCart/{productsInCartId}")
    public ResponseEntity<Void> deleteProductsInCartById(@PathVariable Long productsInCartId) {
        productsInCartDBService.deleteProductsInCartById(productsInCartId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/productsInCart")
    public ResponseEntity<Void> deleteAllProductsInCart() {
        List<ProductsInCart> productsInCartList = productsInCartDBService.getAllProductsInCart();
        productsInCartDBService.deleteProductsInCartList(productsInCartList);
        return ResponseEntity.noContent().build();
    }

}
