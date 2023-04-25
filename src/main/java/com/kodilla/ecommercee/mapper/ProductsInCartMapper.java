package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.ProductsInCart;
import com.kodilla.ecommercee.domain.dto.ProductsInCartDto;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.service.CartDbService;
import com.kodilla.ecommercee.service.ProductDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsInCartMapper {

    private final CartDbService cartDBService;
    private final ProductDbService productDBService;

    public ProductsInCart mapToProductsInCart(ProductsInCartDto productsInCartDto) throws ProductNotFoundException {
        return new ProductsInCart(
                productsInCartDto.getProductsInCartId(),
                cartDBService.getCartById(productsInCartDto.getCartId()),
                productDBService.getProductById(productsInCartDto.getProductId()),
                productsInCartDto.getProductQuantity()
        );
    }

    public ProductsInCartDto mapToProductsInCartDto(ProductsInCart productsInCart) {
        return new ProductsInCartDto(
                productsInCart.getProductsInCartId(),
                productsInCart.getProduct().getProductId(),
                productsInCart.getCartId().getCartId(),
                productsInCart.getProductQuantity()

        );
    }

    public List<ProductsInCart> mapToProductsInCartList(final List<ProductsInCartDto> productsInCartDtosList) {
        List<ProductsInCart> collect = new ArrayList<>();
        for (ProductsInCartDto productsInCartDto : productsInCartDtosList) {
            ProductsInCart productsInCart = null;
            try {
                productsInCart = mapToProductsInCart(productsInCartDto);
            } catch (ProductNotFoundException e) {
                e.printStackTrace();
            }
            collect.add(productsInCart);
        }
        return collect;
    }

    public List<ProductsInCartDto> mapToProductsInCartDtoList(final List<ProductsInCart> productsInCartList) {
        List<ProductsInCartDto> collect = new ArrayList<>();
        for (ProductsInCart productsInCart : productsInCartList) {
            ProductsInCartDto productsInCartDto;
            productsInCartDto = mapToProductsInCartDto(productsInCart);
            collect.add(productsInCartDto);
        }
        return collect;
    }

}

