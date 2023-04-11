package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.ProductsInCart;
import org.springframework.data.repository.CrudRepository;

public interface ProductsInCartRepository extends CrudRepository<ProductsInCart, Long> {

}
