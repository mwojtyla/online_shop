package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductMapper {

    private final ProductsInCartMapper productsInCartMapper;
    private final OrderItemMapper orderItemMapper;
    private final GroupService groupService;

    public Product mapToProduct(final ProductDto productDto) {
        return new Product(
                productDto.getProductId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                groupService.getGroupById(productDto.getGroupId()),
                productsInCartMapper.mapToProductsInCartList(productDto.getProductsInCartDtos()),
                orderItemMapper.mapToOrderItemList(productDto.getOrderItemsDtos())
        );
    }

    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getGroup().getGroupId(),
                productsInCartMapper.mapToProductsInCartDtoList(product.getProductsInCarts()),
                orderItemMapper.mapToOrderItemDtoList(product.getOrderItem())

        );
    }

    public List<Product> mapToProductsList(final List<ProductDto> productsDtosList) {
        return productsDtosList.stream()
                .map(productDto -> new Product(
                        productDto.getProductId(),
                        productDto.getName(),
                        productDto.getDescription(),
                        productDto.getPrice(),
                        groupService.getGroupById(productDto.getGroupId()),
                        productsInCartMapper.mapToProductsInCartList(productDto.getProductsInCartDtos()),
                        orderItemMapper.mapToOrderItemList(productDto.getOrderItemsDtos())
                ))
                .collect(Collectors.toList());
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productsList) {
        return productsList.stream()
                .map(product -> new ProductDto(
                        product.getProductId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getGroup().getGroupId(),
                        productsInCartMapper.mapToProductsInCartDtoList(product.getProductsInCarts()),
                        orderItemMapper.mapToOrderItemDtoList(product.getOrderItem())
                ))
                .collect(Collectors.toList());
    }

}
