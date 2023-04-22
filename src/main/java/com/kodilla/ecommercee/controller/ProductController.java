package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductsInCart;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.domain.dto.ProductsInCartDto;
import com.kodilla.ecommercee.exeption.ProductNotFoundException;
import com.kodilla.ecommercee.exeption.ProductsInCartNotFoundExeption;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.mapper.ProductsInCartMapper;
import com.kodilla.ecommercee.service.GroupService;
import com.kodilla.ecommercee.service.ProductService;
import com.kodilla.ecommercee.service.ProductsInCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/shop/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final GroupService groupService;
    private final ProductsInCartMapper productsInCartMapper;
    private final ProductsInCartService productsInCartService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtos = productMapper.mapToProductDtoList(products);
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/productsInCart")
    public ResponseEntity<List<ProductsInCartDto>> getProductsInCart() {
        List<ProductsInCart> productsInCartList = productsInCartService.getAllProductsInCart();
        List<ProductsInCartDto> productsInCartDtos = productsInCartMapper.mapToProductsInCartDtoList(productsInCartList);
        return ResponseEntity.ok(productsInCartDtos);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) throws ProductNotFoundException {
        Product product = productService.getProductById(productId);
        ProductDto productDto = productMapper.mapToProductDto(product);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/productInCart/{productsInCartId}")
    public ResponseEntity<ProductsInCartDto> getProductInCartById(@PathVariable Long productsInCartId) throws ProductsInCartNotFoundExeption {
        ProductsInCart productsInCart = productsInCartService.getProductsInCartById(productsInCartId);
        ProductsInCartDto productsIncartDto = productsInCartMapper.mapToProductsInCartDto(productsInCart);
        return ResponseEntity.ok(productsIncartDto);
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        groupService.saveGroup(product.getGroup());
        productService.saveProduct(product);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/productsInCart")
    public ResponseEntity<Void> createProductsInCart(@RequestBody ProductsInCartDto productsInCartDto) throws ProductNotFoundException {
        ProductsInCart productsInCart = productsInCartMapper.mapToProductsInCart(productsInCartDto);
        productsInCartService.saveProductsInCart(productsInCart);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto) throws ProductNotFoundException {
        Product productToUpdate = productService.getProductById(productId);
        productToUpdate.setName(productDto.getName());
        productToUpdate.setDescription(productDto.getDescription());
        productToUpdate.setPrice(productDto.getPrice());
        Product updatedProduct = productService.updateProduct(productToUpdate);
        ProductDto updatedProductDto = productMapper.mapToProductDto(updatedProduct);
        return ResponseEntity.ok(updatedProductDto);
    }

    @PutMapping("/productInCart/{productsInCartId}")
    public ResponseEntity<ProductsInCartDto> updateProductsInCart(@PathVariable Long productsInCartId, @RequestBody ProductsInCartDto productsInCartDto) throws ProductsInCartNotFoundExeption {
        ProductsInCart productsInCartToUpdate = productsInCartService.getProductsInCartById(productsInCartId);
        productsInCartToUpdate.setProductQuantity(productsInCartDto.getProductQuantity());
        ProductsInCart updatedProductsInCart = productsInCartService.updateProductsInCart(productsInCartToUpdate);
        ProductsInCartDto updatedProductDto = productsInCartMapper.mapToProductsInCartDto(updatedProductsInCart);
        return ResponseEntity.ok(updatedProductDto);
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/productInCart/{productsInCartId}")
    public ResponseEntity<Void> deleteProductsInCartById(@PathVariable Long productsInCartId) {
        productsInCartService.deleteProductsInCartById(productsInCartId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/productsInCart")
    public ResponseEntity<Void> deleteAllProductsInCart() {
        List<ProductsInCart> productsInCartList = productsInCartService.getAllProductsInCart();
        productsInCartService.deleteProductsInCartList(productsInCartList);
        return ResponseEntity.noContent().build();
    }

}
