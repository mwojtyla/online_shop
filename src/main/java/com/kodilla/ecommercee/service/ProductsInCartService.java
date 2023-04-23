package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductsInCart;
import com.kodilla.ecommercee.exception.ProductsInCartNotFoundExeption;
import com.kodilla.ecommercee.repository.ProductsInCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsInCartService {

    private final ProductsInCartRepository productsInCartRepository;

    public List<ProductsInCart> getAllProductsInCart() {
        return productsInCartRepository.findAll();
    }

    public ProductsInCart getProductsInCartById(final Long productsInCartId) throws ProductsInCartNotFoundExeption {
        return productsInCartRepository.findById(productsInCartId).orElseThrow(ProductsInCartNotFoundExeption::new);
    }

    public void deleteProductsInCartById(final Long productsInCartId) {
        productsInCartRepository.deleteById(productsInCartId);
    }

    public void saveProductsInCart(final ProductsInCart  productsInCart) {
        productsInCartRepository.save(productsInCart);
    }

    public ProductsInCart updateProductsInCart(ProductsInCart updatedProductsInCart) throws ProductsInCartNotFoundExeption {
        ProductsInCart productsInCart = getProductsInCartById(updatedProductsInCart.getProductsInCartId());
        productsInCart.setProduct(updatedProductsInCart.getProduct());
        productsInCart.setProductQuantity(updatedProductsInCart.getProductQuantity());
        return productsInCartRepository.save(productsInCart);
    }

    public void deleteProductsInCartList(final List<ProductsInCart>  productsInCartList) {
        for (ProductsInCart productsInCart : productsInCartList) {
            productsInCartRepository.deleteById(productsInCart.getProductsInCartId());
        }
    }

}